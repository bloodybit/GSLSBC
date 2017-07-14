import React, { Component } from 'react';
import {render} from 'react-dom';
import {
  BrowserRouter as Router,
  Route,
  Link,
  Switch
} from 'react-router-dom'

import Contact  from './common/contact.component.jsx';
import Home     from './common/home.component.jsx';
import Main     from './common/main.component.jsx';
import Login    from './common/login.component.jsx';

render(
  <Router>
    <div>
      <nav className="navbar navbar-dark navbar-custom">
        <button className="navbar-toggler hidden-sm-up" type="button">
      &#9776;
    </button>
        <div className="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">
            <ul className="nav navbar-nav">
                <li className="nav-item nav-item-custom">
                  <Link className="nav-link" to="/search"> <span className="fa fa-eye"></span> Search</Link>
                </li>
                <li className="nav-item nav-item-custom">
                  <Link className="nav-link" to="/main"> <span className="fa fa-plus"></span> New Transaction</Link>
                </li>
                <li className="nav-item nav-item-custom nav-item-last" >
                  <Link className="nav-link" style={{float: "right"}} to="/login"> <span className="fa fa-unlock-alt"></span> Logout</Link>
                </li>
            </ul>
        </div>
    </nav>
 
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/main" component={Main}/>
        <Route path="/search" component={Home}/>
        <Route path="/" component={Login} /> 
      </Switch>
    </div>   
  </Router>,
  document.getElementById('render')
);