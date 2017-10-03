import React, {Component} from 'react';
import ajax from "./Ajax";

class EditBlock extends Component {

    send() {
        ajax('/editWord', 'POST', JSON.stringify({
            id: this.props.values.row.id,
            russian: this.refs.rus.value,
            english: this.refs.eng.value
        }), true).then((row) => {
            this.props.action(this.props.values.arrayId, JSON.parse(row));
            this.props.close()
        });
    }


    render() {
        var display = null;

        if (this.props.values.show) {
            display = <div className="positionAbsolute form-inline">

                <div className="closeSymbol"><a onClick={() => this.props.close()}
                                                href="javascript:void(0);">&times;</a></div>

                <input className="form-control" ref="eng" placeholder='english'
                       defaultValue={this.props.values.row.english}/>

                <input className="form-control" ref="rus" placeholder='русский'
                       defaultValue={this.props.values.row.russian}/>

                <button className="btn btn-danger" onClick={this.send.bind(this)}>изменить</button>
            </div>
        }
        return display;
    }
}

export default EditBlock;