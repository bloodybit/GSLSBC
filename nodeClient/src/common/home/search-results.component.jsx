import React, { Component } from 'react';

class SearchResults extends Component {

    render(){
        return (
            <div className="panel panel-default animated fadeInDown">
                <div className="panel-head">
                    <h3 className="lead">{this.props.socialRecord? this.props.socialRecord.displayName: "Error 404"}</h3>
                </div>
                <div className={"panel-body " + (this.props.socialRecord? '': 'hidden')}>
                    <p>Type: <code>{this.props.socialRecord? this.props.socialRecord.type: ''}</code></p>
                    <p>GID: <samp>{this.props.socialRecord? this.props.socialRecord.globalID: ''}</samp></p>
                    <p>Location: <a>{this.props.socialRecord? this.props.socialRecord.profileLocation: ''}</a></p>
                    <p>Date: {this.props.socialRecord? this.props.socialRecord.datetime: ''}</p>    
                </div>
                <div className={"panel-body " + (this.props.socialRecord? 'hidden': '')}>
                    <p className="text-center">{this.props.errorMessage}</p>
                    <div>
                        <div className="no-sr" ></div>  
                    </div>
                </div>
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