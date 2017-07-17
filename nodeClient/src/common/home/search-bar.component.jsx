import React, { Component } from 'react';
import { getSocialRecord } from './../../../logic/transaction';

class SearchBar extends Component {
    
    constructor() {
        super();
        this.defaultButtonText = 'Search';
        this.state = {
            buttonText: this.defaultButtonText,
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

        let self = this;

        console.log("input", this.state.searchText);
        getSocialRecord(this.state.searchText)
            .then(socialRecord => {
                self.setState({buttonText: self.defaultButtonText});
                self.props.searchCallback(null, socialRecord);
            })
            .catch(error => {
                self.props.searchCallback(error, null);
            });
        
        // TODO: make query...
        // let searchResults = [{
        //     name: "Alice", 
        //     mail: "alice@mail.com",
        //     profile: "www.facebook.com/alice"
        // }, {
        //     name: "Bob", 
        //     mail: "bob@mail.com",
        //     profile: "www.facebook.com/bob"
        // }]
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