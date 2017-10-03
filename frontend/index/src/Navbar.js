import React, {Component} from 'react';
import ajax from "./Ajax";


class Navbar extends Component {

    constructor(props) {
        super(props);
        this.state = {logged: false};
        this.logout = this.logout.bind(this);
        this.drawAdminOptIfneed = this.drawAdminOptIfneed.bind(this);
    }

    componentDidMount() {
        ajax('/userIsLogged', 'POST', null, true).then(function (ans) {
            this.setState({logged: JSON.parse(ans)});
        }.bind(this));
    }


    logout() {
        ajax('/logout', 'POST', null, true).then(function () {
            window.location.href = "/login";
        });
    }

    drawAdminOptIfneed() {
        return this.state.logged ? <div className="navbar-brand">
            <a onClick={() => this.props.callback()} href="javascript:void(0);">админка(открыть/закрыть)</a>
        </div> : <p className="navbar-text">Для добавления своих слов, а также их редактирования/удаления, необходимо
            залогироваться</p>
    }

    drawLoginOrLogout() {
        return this.state.logged ?
            <div className="navbar-brand"><a onClick={this.logout} href="javascript:void(0);">выйти</a></div> :
            <div className="navbar-brand"><a href="/login">войти</a></div>;
    }

    render() {
        return <nav className="navbar navbar-inverse">
            <div className="container-fluid">
                <div className="nav navbar-nav">
                    {this.drawAdminOptIfneed()}
                </div>
                <div className="nav navbar-nav navbar-right">
                    {this.drawLoginOrLogout()}
                </div>
            </div>
        </nav>
    }
}

export default Navbar