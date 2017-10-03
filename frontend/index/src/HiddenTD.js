import React, {Component} from 'react';


class HiddenTD extends Component {

    constructor(props) {
        super(props);
        this.state = ({hidden: true});
    }


    render() {
        return (
            <td> {this.state.hidden ?
                <a onClick={() => this.setState({hidden: false})}
                   href="javascript:void(0);">показать</a> : this.props.children} </td>
        )
    }
}


export default HiddenTD;