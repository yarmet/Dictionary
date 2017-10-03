import React, {Component} from 'react';
import ajax from "./Ajax";

class RemoveBlock extends Component {


    render() {
        var display = null;
        if (this.props.values.show) {
            display =
                <div className="positionAbsolute form-inline">

                    <div className="closeSymbol"><a onClick={() => this.props.close()}
                                                    href="javascript:void(0);">&times;</a></div>

                    <button className="btn btn-danger" onClick={() =>
                        ajax('/deleteWord', 'POST', JSON.stringify({id: this.props.values.id}), true)
                            .then(() => {
                                this.props.action(this.props.values.arrayId);
                                this.props.close();
                            })}>уверены что хотите удалить?
                    </button>
                </div>
        }
        return display;
    }
}

export default RemoveBlock;