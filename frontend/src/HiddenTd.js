import React, {Component} from 'react';


class HiddenTd extends Component {

    constructor(props) {
        super(props);
        this.state = ({hidden: true});
        this.click = this.click.bind(this);
    }

    click() {
        this.setState({hidden: false})
    }

    render() {
        return (
            <td> {this.state.hidden ?
                <a onClick={this.click} href="javascript:void(0);">показать</a> : this.props.children} </td>
        )
    }
}


export default HiddenTd;