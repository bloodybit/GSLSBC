import React, { Component } from 'react';

import DragHere from '../drag-here.component.jsx';

class TransactionForm extends Component {
    constructor() {
        super();
        this.state = {
            buttonText: "Send to GSLS",
            showElement: false
        }
        this.toggleFormElement = this.toggleFormElement.bind(this);
        this.sendTransactionToGsls = this.sendTransactionToGsls.bind(this);
    }
    
    toggleFormElement(e, show) {
        e.preventDefault();
        this.setState({showElement: show});
        return false;
    }

    sendTransactionToGsls(e) {
        e.preventDefault();
        this.setState({buttonText: "Sending..."});
    }

    render(){
        return (
            <form className="add-poi-form">
                <h3>New Transaction</h3>
                <hr />
                <div className="btn-group">
                    <button className="btn btn-primary btn-sm pull-right" onClick={(e) => this.toggleFormElement(e, false)}>Create</button>
                    <button className="btn btn-warning btn-sm pull-right" onClick={(e) => this.toggleFormElement(e, true)}>Update</button>
                </div>
                <div id="error-box"></div>
                <DragHere whatToDrag={"Drag Social Record here"}/>
                <p className="lead">Transaction info:</p>
                <div className="form-group">
                <label htmlFor="project-description">Nonce:</label>
                <input type="number" className="form-control" id="transaction-nonce" placeholder="Nonce..." />
                </div>
                <div className="form-group" className={this.state.showElement ? '' : 'hidden'}>
                    <label htmlFor="project-description">GlobalID:</label>
                    <input type="text" className="form-control" id="sr-gloabal-id" placeholder="Global ID..." />
                </div> 
                    <button type="submit" className="btn btn-primary btn-sm pull-right" onClick={this.sendTransactionToGsls}>{this.state.buttonText}</button>
            </form>
        );
    }
}

export default TransactionForm