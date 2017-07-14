import React, { Component } from 'react';

class SearchBar extends Component {
    
    constructor() {
        super();
        this.state = {
            buttonText: 'Search',
            searchText: ''
            
        };
        this.handleChange = this.handleChange.bind(this);
        this.searchSocialRecord = this.searchSocialRecord.bind(this);
    }

    handleChange(e) {
        this.setState({ searchText: e.target.value });
        console.log("query: ", this.state.searchText);
    }

    searchSocialRecord(e) {
        e.preventDefault();
        this.setState({ buttonText: "Searching..." });
        console.log("input", this.state.searchText);
        
        // TODO: make query...
        let searchResults = [{
            name: "Alice", 
            mail: "alice@mail.com",
            profile: "www.facebook.com/alice"
        }, {
            name: "Bob", 
            mail: "bob@mail.com",
            profile: "www.facebook.com/bob"
        }]

        this.props.searchCallback(searchResults);
    }

    render(){
        return (
            <div className="input-group">
                <input type="text" className="search-query form-control" placeholder="Search..."  onChange={ this.handleChange }/>
                <span className="input-group-btn">
                <button type="button" className="btn btn-primary" onClick={this.searchSocialRecord}>{this.state.buttonText}</button>
                </span>
            </div>
        );
    }
}

export default SearchBar