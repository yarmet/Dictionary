import React, {Component} from 'react';


class RemoveBlock extends Component {

    constructor(props) {
        super(props);
    }


    render() {
        var display = null;
        if (this.props.values.show) {
            display =
                <div className="positionAbsolute form-inline">

                    <div className="closeSymbol"><a onClick={() => {
                        this.props.callback(null, null)
                    }} href="javascript:void(0);">&times;</a></div>

                    <button className="btn btn-danger" onClick={() => {
                        this.props.callback(this.props.values.id, this.props.values.arrayId);
                    }}>уверены что хотите удалить?
                    </button>
                </div>
        }
        return display;
    }
}

export default RemoveBlock;