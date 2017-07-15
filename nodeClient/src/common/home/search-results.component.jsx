import React, { Component } from 'react';

class SearchResults extends Component {
    render(){
        return (
            <div>
                {this.props.socialRecord? this.props.socialRecord: this.props.error}
            </div>
        );
    }
}

/*
{this.props.results.map((element, index) => (
                    <div className=""  key={index}>
                        Name: {element.name}, Email: {element.email}
                    </div>
                ))}

*/ 
export default SearchResults