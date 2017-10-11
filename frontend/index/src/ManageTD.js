import React, {Component} from 'react';

class ManageTD extends Component {

    constructor(props) {
        super(props);
        this.drawTdIfNeed = this.drawTdIfNeed.bind(this);
        this.drawUrlIfNeed = this.drawUrlIfNeed.bind(this);
    }


    drawUrlIfNeed() {
        return this.props.blocked ? <span>{this.props.children}</span> :
            <a onClick={() => this.props.callBack()} href="javascript:void(0);">{this.props.children}</a>
    }

    drawTdIfNeed() {
        return this.props.admin ? <td>{this.drawUrlIfNeed()} </td> : null;
    }

    render() {
        return this.drawTdIfNeed()
    }
}

export default ManageTD;