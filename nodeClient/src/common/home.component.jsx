import React, { Component } from 'react';
import SearchBar from './home/search-bar.component.jsx';
import SearchResults from './home/search-results.component.jsx';

class Home extends Component {
    constructor() {
        super();
        this.state = {
            searchText: '',
            socialRecord: ''
        };
        this.gotResults = this.gotResults.bind(this);
        this.displayResults = this.displayResults.bind(this);
    }

    gotResults(error, socialRecord) {
        if (error) {
            console.log(error);
            this.setState({ errorMessage: error, socialRecord: null });
            this.displayResults();
        } else {
            console.log(socialRecord);
            this.setState({ socialRecord, errorMessage: null});
            this.displayResults();
        }
    }

    displayResults() {
        console.log("displayRes");
        if (this.state.socialRecord) {
            return (<SearchResults socialRecord={ this.state.socialRecord } errorMessage=""/>);
        } else {
            console.log("else");
            return (<SearchResults errorMessage={ this.state.errorMessage } socialRecord=""/>);
        }
    }

    render(){
        return (
            <div className="animated fadeInUp" id="search-box-container">
                <div className="row">
                    <div className="col-xs-12 col-sm-8 col-md-8 col-lg-8 col-sm-offset-2 col-md-offset-2 col-lg-offset-2">
                        <SearchBar searchCallback={ this.gotResults }/>
                        <hr />
                        {this.state.socialRecord? this.displayResults(): ''}
                        {this.state.errorMessage? this.displayResults(): ''}
                    </div>
                </div>
            </div>
        );
    }
}

export default Home