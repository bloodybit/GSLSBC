import React, { Component } from 'react';
import SearchBar from './home/search-bar.component.jsx';
import SearchResults from './home/search-results.component.jsx';

class Home extends Component {
    constructor() {
        super();
        this.state = {
            searchText: '',
            searchResults: []
        };
        this.gotResults = this.gotResults.bind(this);
        this.displayResults = this.displayResults.bind(this);
        console.log(window.currentWallet);
    }

    gotResults(searchResults) {
        // do something 

        this.setState({searchResults});
        this.displayResults();
    }

    displayResults() {
        if(this.state.searchResults){
            return (
                <SearchResults results={this.state.searchResults}/>
            );
        }
    }

    render(){
        return (
            <div className="animated fadeInUp" id="search-box-container">
                <div className="row">
                    <div className="col-xs-12 col-sm-8 col-md-6 col-lg-6 col-sm-offset-2 col-md-offset-3 col-lg-offset-3">
                        <SearchBar searchCallback={this.gotResults}/>
                        <hr />
                        {this.displayResults()}
                    </div>
                </div>
            </div>
        );
    }
}

export default Home