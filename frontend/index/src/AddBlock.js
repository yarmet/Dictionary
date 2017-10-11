import React, {Component} from 'react';
import ajax from "./Ajax";


class AddBlock extends Component {

    render() {
        return <div className="positionAbsolute form-inline">

            <div className="closeSymbol"><a onClick={() => this.props.close()}
                                            href="javascript:void(0);">&times;</a></div>

            <input className="form-control" ref="eng" placeholder='english'/>
            <input className="form-control" ref="rus" placeholder='русский'/>

            <button className="btn btn-danger" onClick={() =>
                ajax('/addWord', 'POST', JSON.stringify({
                    russian: this.refs.rus.value,
                    english: this.refs.eng.value
                }), true)
                    .then((row) => {
                        this.props.callback(JSON.parse(row));
                        this.props.close()
                    })
            }>добавить
            </button>
        </div>
    }
}

export default AddBlock;