import React, {Component} from 'react';
import ajax from "./Ajax";


class Navbar extends Component {

    constructor(props) {
        super(props);
        this.state = {logged: false};
        this.adminModeToggle = this.adminModeToggle.bind(this);
        this.logout = this.logout.bind(this);
        this.drawAdminOptIfneed = this.drawAdminOptIfneed.bind(this);
    }

    componentDidMount() {
        ajax('/userIsLogged', 'POST', null, true).then(function (ans) {
            this.setState({logged: JSON.parse(ans)});
        }.bind(this));
    }

    adminModeToggle() {
        this.props.callback();
    }

    logout() {
        ajax('/logout', 'POST', null, true).then(function () {
            window.location.href = "/login";
        });
    }

    drawAdminOptIfneed() {
        return this.state.logged ? <div className="navbar-brand">
            <a onClick={this.adminModeToggle} href="javascript:void(0);">админка(открыть/закрыть)</a>
        </div> : null
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