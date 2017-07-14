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

    fileDragged(walletFile) {
        this.setState({ walletUrl: walletFile.path });
    }
    
    login(e) {
        e.preventDefault();
        this.setState({
            loginText: "Loading your credentials...",
            redirectionTriggered: true
        });
        let self = this;
        console.log(self);
        walletUtils.getWalletKey(this.state, function(wallet){
            console.log(wallet);
            window.currentWallet = wallet;
            self.setState({redirect: true});
            self.redirect();
        });
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
                    <input type="submit" className="btn btn-lg btn-primary btn-block" name="submit" onClick={this.login} id="login" value={this.state.loginText} />
                    <div id="loading-box"></div>
                    <div id="error-box"></div>
                    {this.redirect()}

                </fieldset>
            </form>
        );
    }
}

export default LoginForm