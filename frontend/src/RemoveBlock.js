import React, {Component} from 'react';


class RemoveBlock extends Component {

    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(this.props.values.id, this.props.values.arrayId);
    }

    closeBlock() {
        this.props.callback(null, null)
    }

    render() {
        var display = null;
        if (this.props.values.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="javascript:void(0);">&times;</a></div>
                <button className="btn btn-danger" onClick={this.getResult}>уверены что хотите удалить?</button>
            </div>
        }
        return display;
    }
}

export default RemoveBlock;