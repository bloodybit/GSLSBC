import React, { Component } from 'react';
import LoginForm from './login/login-form.component.jsx';

class Login extends Component {
    render(){
        return (
            <div className="container" id="login">
            <div id="background"></div>
            <div className="row vertical-offset-100">
                <div className="col-xs-12 col-sm-8 col-md-6 col-lg-6 col-sm-offset-2 col-md-offset-3 col-lg-offset-3">
                    <div className="panel panel-default animated fadeInDown">
                        <div className="panel-heading">
                            <div className="row-fluid user-row">
                                <h1 className="text-center">Social Record</h1>
                            </div>
                        </div>
                        <div className="panel-body">
                            <LoginForm />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        );
    }
}


export default Login