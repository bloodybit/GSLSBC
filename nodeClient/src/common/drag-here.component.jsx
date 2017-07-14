import React, { Component } from 'react';
import Dropzone from 'react-dropzone';

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
        this.setState({whatToDrag: acceptedFiles[0].name});
        if(this.props.fileDragged) {
            this.props.fileDragged(acceptedFiles[0]);
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