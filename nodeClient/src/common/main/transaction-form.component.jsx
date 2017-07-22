import React, { Component } from 'react';
import DragHere from '../drag-here.component.jsx';
import jsonFile from 'jsonfile';
import { 
    addSocialRecord, 
    createRawTrans, 
    sendTransaction, 
    updateSocialRecord } from './../../../logic/transaction';

class TransactionForm extends Component {

    constructor() {
        super();
        this.defaultButtonText = "Send to GSLS";
        this.state = {
            buttonText: this.defaultButtonText,
            showElement: false, 
            activateCreate: true,
            activateUpdate: false,
            functionName: 'addSocialRecord'
        }

        this.createSocialRecord = this.createSocialRecord.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.sendTransactionToGsls = this.sendTransactionToGsls.bind(this);
        this.setSocialRecord = this.setSocialRecord.bind(this);
        this.toggleFormElement = this.toggleFormElement.bind(this);
        this.updateSocialRecord = this.updateSocialRecord.bind(this);
    }
    
    toggleFormElement(e, show, functionName) {
        e.preventDefault();
        this.setState({functionName, activateUpdate: show, activateCreate: !show});
        return false;
    }

    handleChange(e) {
        this.setState({ globalID: e.target.value });
        console.log("query: ", this.state.searchText);
    }
    
    sendTransactionToGsls(e) {
        e.preventDefault();
        this.setState({buttonText: "Preparing..."});
        let userAddress = null;
        const socialRecord = this.state.socialRecord; // get the SR from the state
        const self = this;

        createRawTrans(this.state.functionName, [socialRecord.globalID, JSON.stringify(socialRecord)])
            .then(rawTransaction => {
                console.log(socialRecord);
                self.setState({buttonText: "Sending..."}); 
                return sendTransaction(rawTransaction);
            })
            .then(response => {
                console.log(response.txHash);
                self.setState({successMessage: "Transaction sent successfully", transactionHash: response.txHash, buttonText: self.defaultButtonText});
                
            })
            .catch(error => {
                console.error("ERROR: ", error);
                self.setState({errorMessage: error});
            })
            .catch(error => {
                console.error("ERROR: ", error);
                self.setState({errorMessage: error});
            });
    }

    createSocialRecord(e) {
        e.preventDefault();
        this.setState({buttonText: "Preparing..."});
        let userAddress = null;
        const socialRecord = this.state.socialRecord; // get the SR from the state
        const self = this;

        addSocialRecord(socialRecord.globalID, JSON.stringify(socialRecord))
            .then( (result) => {
                console.log('result', result);
            })
            .catch( (error) => {
                self.setState({ errorMessage: error });
            });
    }
    
    updateSocialRecord(e) {
        e.preventDefault();
        this.setState({buttonText: "Preparing..."});
        let userAddress = null;
        const socialRecord = this.state.socialRecord; // get the SR from the state
        const self = this;

        updateSocialRecord(socialRecord.globalID, JSON.stringify(socialRecord))
            .then( (result) => {
                console.log('result', result);
            })
            .catch( (error) => {
                self.setState({ errorMessage: error });
            });
    }

    setSocialRecord(socialRecord) {
        this.setState({socialRecord, showElement: true});
    }

    render(){
        return (
            <form className="add-poi-form">
                <div className="error">{this.state.errorMessage}</div>
                <div className="result">
                    <p>{this.state.successMessage}</p>
                    <samp className="small">{this.state.transactionHash}</samp>
                </div>
                <h3>New Transaction</h3>
                <hr />
                <div className="btn-group">
                    <button className={"btn btn-sm pull-right " + (this.state.activateCreate? 'btn-primary': 'btn-default')} onClick={(e) => this.toggleFormElement(e, false, 'addSocialRecord')}>Create</button>
                    <button className={"btn btn-sm pull-right " + (this.state.activateUpdate? 'btn-warning': 'btn-default')} onClick={(e) => this.toggleFormElement(e, true, 'updateSocialRecord')}>Update</button>
                </div>
                <div id="error-box"></div>
                <DragHere whatToDrag={"Drag Social Record here"} fileDragged={this.setSocialRecord}/>
                <p className="lead"> Your Social Record:</p>
                <div className="form-group" className={this.state.showElement ? '' : 'hidden'}>
                    <p>Name: <code>{this.state.socialRecord? this.state.socialRecord.displayName: ''}</code></p>
                    <p>Type: <code>{this.state.socialRecord? this.state.socialRecord.type: ''}</code></p>
                    <p>GID: <samp>{this.state.socialRecord? this.state.socialRecord.globalID: ''}</samp></p>
                    <p>Location: <a>{this.state.socialRecord? this.state.socialRecord.profileLocation: ''}</a></p>
                    <p>Date: {this.state.socialRecord? this.state.socialRecord.datetime: ''}</p>
                </div>
                    <button type="submit" className="btn btn-primary btn-sm pull-right" onClick={this.sendTransactionToGsls}>{this.state.buttonText}</button> 
            </form>
        );
    }
}

export default TransactionForm