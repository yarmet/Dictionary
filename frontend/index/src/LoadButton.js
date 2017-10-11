import React, {Component} from 'react';
import ajax from "./Ajax";

class LoadButton extends Component {

    constructor(props) {
        super(props);
        this.loadWords = this.loadWords.bind(this);
    }

    componentDidMount() {
        this.loadWords();
    }

    loadWords() {
        ajax(this.props.loadAnyRows ? '/getWords' : '/getLastWords', 'GET', null, true)
            .then(rows => this.props.action(JSON.parse(rows)))
    }


    render() {
        return <div id="loadBtn">
            <button className="btn btn-default shadow" onClick={this.loadWords}>загрузить</button>
        </div>
    }
}

export default LoadButton;