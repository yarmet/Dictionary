import React, {Component} from 'react';
import Languages from "./Languages";


class EditBlock extends Component {
    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(this.props.values.arrayId,
            {
                id: this.props.values.id,
                russian: this.refs.rus.value,
                english: this.refs.eng.value
            })
    }

    closeBlock() {
        this.props.callback(null, null);
    }

    render() {
        var display = null;

        if (this.props.values.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="javascript:void(0);">&times;</a></div>
                <input className="form-control" ref="eng" placeholder={Languages.ENGLISH}
                       defaultValue={this.props.values.eng}/>

                <input className="form-control" ref="rus" placeholder={Languages.RUSSIAN}
                       defaultValue={this.props.values.rus}/>

                <button className="btn btn-danger" onClick={this.getResult}>изменить</button>
            </div>
        }
        return display;
    }
}

export  default EditBlock;