import React, {Component} from 'react';


class LanguageSwitcher extends Component {

    constructor(props) {
        super(props);
        this.state = {rus: this.props.initValue};
    }

    render() {
        return <div className="group1 shadow">
            <label> <input type="radio" name="lang"
                           onClick={
                               () => {
                                   this.setState({rus: true});
                                   this.props.action(true);
                               }
                           }
                           defaultChecked={this.state.rus === true}/> русский </label>

            <label> <input type="radio" name="lang"
                           onClick={
                               () => {
                                   this.setState({rus: false});
                                   this.props.action(false);
                               }
                           }
                           defaultChecked={this.state.rus === false}/> english </label>
        </div>
    }
}


export default LanguageSwitcher;