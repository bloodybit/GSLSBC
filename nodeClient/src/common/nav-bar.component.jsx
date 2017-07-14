import React, { Component } from 'react';
import { Link } from 'react-router-dom'

class NavBar extends Component {

    render() {
        return (
        <nav className="navbar navbar-dark navbar-custom">
            <button className="navbar-toggler hidden-sm-up" type="button">&#9776;</button>
            <div className="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">
                <ul className="nav navbar-nav">
                    <li className="nav-item nav-item-custom">
                        <Link className="nav-link" to="/"> <span className="fa fa-eye"></span> Search </Link>
                    </li>
                    <li className="nav-item nav-item-custom">
                        <Link className="nav-link" to="/main"> <span className="fa fa-plus"></span> New Transaction </Link>
                    </li>
                    <li className="nav-item nav-item-custom nav-item-last" >
                        <Link className="nav-link" style={{float: "right"}} to="/login"> <span className="fa fa-unlock-alt"></span> Logout </Link>
                    </li>
                </ul>
            </div>
        </nav>
        );
    }
    
}

export default NavBar 