import React, {Component} from 'react';
import Languages from "./Languages";


class AddBlock extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        var display = null;
        if (this.props.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={ ()=>{this.props.callback(null, null)}  } href="javascript:void(0);">&times;</a></div>


                <input className="form-control" ref="eng" placeholder={Languages.ENGLISH}/>
                <input className="form-control" ref="rus" placeholder={Languages.RUSSIAN}/>
                <button className="btn btn-danger" onClick={  ()=>{ this.props.callback(this.refs.rus.value, this.refs.eng.value); }  }>добавить</button>
            </div>
        }
        return display;
    }
}

export default AddBlock;