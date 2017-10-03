import React, {Component} from 'react';

class LoadChoiceSwitcher extends Component {

    constructor(props) {
        super(props);
        this.state = {loadAll: this.props.initValue};
    }

    render() {
        return <div className="group1 shadow">
            <label> <input type="radio" name="loadOpt"
                           onClick={
                               () => {
                                   this.setState({loadAll: true});
                                   this.props.action(true);
                               }
                           }
                           defaultChecked={this.state.loadAll === true}/> загрузить все </label>

            <label> <input type="radio" name="loadOpt"
                           onClick={
                               () => {
                                   this.setState({loadAll: false});
                                   this.props.action(false);
                               }
                           }
                           defaultChecked={this.state.loadAll === false}/> загрузить за последний месяц </label>
        </div>
    }
}


export default LoadChoiceSwitcher;