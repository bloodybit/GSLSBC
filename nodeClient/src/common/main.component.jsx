import React, {Component} from 'react';

import TransactionForm from './main/transaction-form.component.jsx';

class Main extends Component {
    render(){
        return (
            <div className="animated fadeInUp">
                <div className="row">
                    <div className="col-xs-12 col-sm-8 col-md-8 col-lg-8 col-sm-offset-2 col-md-offset-2 col-lg-offset-2">
                        <TransactionForm />
                    </div>
                </div>
            </div>
        );
    }
}

export default Main