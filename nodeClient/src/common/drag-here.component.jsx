import React, { Component } from 'react';
import Dropzone from 'react-dropzone';
import jsonFile from 'jsonfile';

// Utility method
String.prototype.isJsonFile = function(){
    return this.endsWith('.json');
}

class DragHere extends Component {
    constructor() {
        super();
        this.onDrop = this.onDrop.bind(this);
        this.state = {
            whatToDrag: ''
        };
    }

    onDrop(acceptedFiles, rejectedFiles) {
        console.log(acceptedFiles);
        acceptedFiles = acceptedFiles[0]; // consider only the first
        this.setState({whatToDrag: acceptedFiles.name});
        let self = this;
        if (this.props.fileDragged && acceptedFiles.name.isJsonFile()) {
            
            jsonFile.readFile(acceptedFiles.path, function(err, file) {
                if (err) {
                    console.error("ERROR: ",err);
                } 
                // call back function
                console.log("File read", file);
                self.props.fileDragged(file);
            });
        } else {
            console.error("ERROR: No callback function has been specified, or the file is not json");
        }
    }

    render(){
        return (
            <Dropzone className="drag-here" id="drag-here" onDrop={this.onDrop}>
                <p className="text-center"><b>{this.state.whatToDrag ? this.state.whatToDrag: this.props.whatToDrag}</b></p>                 
            </Dropzone>
        );
    }
}

export default DragHere