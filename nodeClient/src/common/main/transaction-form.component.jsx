import React, { Component } from 'react';
import DragHere from '../drag-here.component.jsx';

import jsonFile from 'jsonfile';
import { 
    addSocialRecord, 
    createRawTrans, 
    sendTransaction, 
    subscribeToEvent,
    test, 
    test2, 
    updateSocialRecord } from './../../../logic/transaction';

class TransactionForm extends Component {
    constructor() {
        super();
        this.defaultButtonText = "Send to GSLS";
        this.state = {
            buttonText: this.defaultButtonText,
            showElement: false
        }

        this.createSocialRecord = this.createSocialRecord.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.sendTransactionToGsls = this.sendTransactionToGsls.bind(this);
        this.setSocialRecord = this.setSocialRecord.bind(this);
        this.testContractCreation = this.testContractCreation.bind(this);
        this.testECRecover = this.testECRecover.bind(this);
        this.toggleFormElement = this.toggleFormElement.bind(this);
        this.updateSocialRecord = this.updateSocialRecord.bind(this);

        // subscribeToEvent(['SocialRecordAdded', 'SocialRecordUpdated'], function(error, result) {
        //     if (error) {
        //         console.log(error);
        //     } else {
        //         console.log(result);
        //     }
        // });

    }
    
    toggleFormElement(e, show) {
        e.preventDefault();
        this.setState({showElement: show});
        return false;
    }

    testContractCreation(e) {
        e.preventDefault();
        test(e, (contractAddress) => console.log(contractAddress));
    }

    testECRecover(e) {
        e.preventDefault();
        test2(e, (userAddress) => console.log(userAddress));
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

        createRawTrans([socialRecord.globalID, JSON.stringify(socialRecord)])
            .then(rawTransaction => {
                console.log(socialRecord);
                self.setState({buttonText: "Sending..."}); 
                return sendTransaction(rawTransaction);
            })
            .then(transactionHash => {
                console.log(transactionHash);
                self.setState({transactionHash: "Message sent successfully", buttonText: self.defaultButtonText});
                
            })
            .catch(error => {
                self.setState({errorMessage: error});
            })
            .catch(error => {
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
        this.setState({socialRecord});
    }

    render(){
        return (
            <form className="add-poi-form">
                <div className="error">{this.state.errorMessage}</div>
                <div className="result">{this.state.transactionHash}</div>
                <h3>New Transaction</h3>
                <button className="btn btn-primary btn-sm pull-right" onClick={(e) => this.testContractCreation(e, false)}>Test</button>

                <hr />
                <div className="btn-group">
                    <button className="btn btn-primary btn-sm pull-right" onClick={(e) => this.toggleFormElement(e, false)}>Create</button>
                    <button className="btn btn-warning btn-sm pull-right" onClick={(e) => this.toggleFormElement(e, true)}>Update</button>
                </div>
                <div id="error-box"></div>
                <DragHere whatToDrag={"Drag Social Record here"} fileDragged={this.setSocialRecord}/>
                <p className="lead">Transaction info:</p>
                <div className="form-group">
                <label htmlFor="project-description">Nonce:</label>
                <input type="number" className="form-control" id="transaction-nonce" placeholder="Nonce..." />
                </div>
                <div className="form-group" className={this.state.showElement ? '' : 'hidden'}>
                    <label htmlFor="project-description">GlobalID:</label>
                    <input type="text" className="form-control" id="sr-gloabal-id" placeholder="Global ID..." onChange={ this.handleChange }/>
                </div> 
                    <button type="submit" className="btn btn-primary btn-sm pull-right" className={this.state.showElement ? 'hidden' : ''} onClick={this.createSocialRecord}>{this.state.buttonText}</button>
                    <button type="submit" className="btn btn-primary btn-sm pull-right" className={this.state.showElement ? '' : 'hidden'} onClick={this.updateSocialRecord}>{this.state.buttonText}</button>
            </form>
        );
    }
}

export default TransactionForm