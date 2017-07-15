import React, { Component } from 'react';
import DragHere from '../drag-here.component.jsx';
import {Redirect} from 'react-router';

const walletUtils =  require('../../../logic/wallet');

class LoginForm extends Component {
    
    constructor() {
        super();
        this.state = {
            loginText: "Login",
            redirect: false
        }

        this.fileDragged = this.fileDragged.bind(this);
        this.handleChanges = this.handleChanges.bind(this);
        this.login = this.login.bind(this);
        this.redirect = this.redirect.bind(this);
    }
    
    handleChanges(e) {
        this.setState({ password: e.target.value });
    }

    fileDragged(walletData) {
        this.setState({ walletData });
    }
    
    login(e) {
        e.preventDefault();
        this.setState({
            loginText: "Loading your credentials...",
            redirectionTriggered: true
        });

        let self = this;
        console.log(self);
        if (this.state.password && this.state.walletData) {
            walletUtils.getWalletKey(this.state.walletData, this.state.password, function(error, wallet){
                if (error) {
                    console.log(error);
                    self.setState({errorMessage: "Invalid password or wallet file", loginText: "Login"})
                } else {
                    console.log(wallet);
                    window.currentWallet = wallet; // set the wallet
                    self.setState({redirect: true});
                    self.redirect();
                }
            });
        } else {
            this.setState({errorMessage: "Elemets missing", loginText: "Login"});
        }
        
    }

    redirect() {
        if (this.state.redirect) {
            return (
                <Redirect to="/main" push />
            );
        }
    }
    render(){
        return (
            <form acceptCharset="UTF-8" role="form" className="form-signin">
                <fieldset>
                    <label className="panel-login"><div className="login_result"></div></label>
                    <input type="password" className="form-control" placeholder="Wallet Password" id="password" onChange={this.handleChanges}/>
                    <DragHere whatToDrag={"Drag Wallet here"} fileDragged={this.fileDragged}/>
                    <input type="submit" className="btn btn-lg btn-primary btn-block" name="submit" onClick={this.login} value={this.state.loginText} />
                    <div id="error-box" className="text-center">{this.state.errorMessage}</div>
                    {this.redirect()}

                </fieldset>
            </form>
        );
    }
}

export default LoginForm