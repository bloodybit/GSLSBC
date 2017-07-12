import React, { Component } from 'react';

import {render} from 'react-dom';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'

import Main from './common/main.component.jsx';



class Home extends Component {
    render(){
        return (<h1>Home Page</h1>);
    }
}




render(
  <Router>
    <div>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/Main">Main</Link></li>
        <li><Link to="/contact">Contact</Link></li>
      </ul>

      <hr/>

      <Route exact path="/" component={Home} />
      <Route path="/Main" component={Main}/>
    </div>   
  </Router>,
  document.getElementById('container')
);