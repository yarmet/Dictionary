import React, {Component} from 'react';
import ajax from "./Ajax";

class RemoveBlock extends Component {


    render() {
        return <div className="positionAbsolute form-inline">


            <div className="closeSymbol"><a onClick={() => this.props.close()}
                                            href="javascript:void(0);">&times;</a></div>


            <button className="btn btn-danger"
                    onClick={() =>
                        ajax('/deleteWord', 'POST', JSON.stringify({id: this.props.values.row.id}), true)
                            .then(() => {
                                this.props.callback(this.props.values.idInArray);
                                this.props.close();
                            })}>уверены что хотите удалить?
            </button>


        </div>
    }
}

export default RemoveBlock;