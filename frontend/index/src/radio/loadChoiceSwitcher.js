import React, {Component} from 'react';

class LoadChoiceSwitcher extends Component {

    constructor(props) {
        super(props);
        this.state = {loadAnyRows: this.props.initValue};
    }

    render() {
        return <div className="group1 shadow">
            <label> <input type="radio" name="loadOpt"
                           onClick={
                               () => {
                                   this.setState({loadAnyRows: true});
                                   this.props.action(true);
                               }
                           }
                           defaultChecked={this.state.loadAnyRows === true}/> загрузить все </label>

            <label> <input type="radio" name="loadOpt"
                           onClick={
                               () => {
                                   this.setState({loadAnyRows: false});
                                   this.props.action(false);
                               }
                           }
                           defaultChecked={this.state.loadAnyRows === false}/> загрузить за последний месяц </label>
        </div>
    }
}


export default LoadChoiceSwitcher;