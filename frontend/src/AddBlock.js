import React, {Component} from 'react';
import Languages from "./Languages";


class AddBlock extends Component {

    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(this.refs.rus.value, this.refs.eng.value);
    }

    closeBlock() {
        this.props.callback(null, null)
    }

    render() {
        var display = null;
        if (this.props.show) {
            document.body.classList.add('transparent');
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="javascript:void(0);">&times;</a></div>
                <input className="form-control" ref="eng" placeholder={Languages.ENGLISH}/>
                <input className="form-control" ref="rus" placeholder={Languages.RUSSIAN}/>
                <button className="btn btn-danger" onClick={this.getResult}>добавить</button>
            </div>
        } else {
            document.body.classList.remove('transparent');
        }
        return display;
    }
}

export default AddBlock;