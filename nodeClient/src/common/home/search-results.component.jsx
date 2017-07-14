import React, { Component } from 'react';

class SearchResults extends Component {
    render(){
        return (
            <div>
                {this.props.results.map((element, index) => (
                    <div className=""  key={index}>
                        Name: {element.name}, Email: {element.email}
                    </div>
                ))}
            </div>
        );
    }
}

export default SearchResults