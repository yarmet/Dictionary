import React, {Component} from 'react';

class ManageTd extends Component {

    constructor(props) {
        super(props);
        this.click = this.click.bind(this);
        this.drawTdIfNeed = this.drawTdIfNeed.bind(this);
        this.drawUrlIfNeed = this.drawUrlIfNeed.bind(this);
    }

    click(row, arrayId) {
        this.props.callBack(row, arrayId);
    }

    drawUrlIfNeed() {
        return this.props.urlsBlocked ? <span>{this.props.children}</span> :
            <a onClick={this.click.bind(null, this.props.row, this.props.arrayId)}
               href="javascript:void(0);">{this.props.children}</a>
    }

    drawTdIfNeed() {
        return this.props.admin ? <td>{this.drawUrlIfNeed()} </td> : null;
    }

    render() {
        return this.drawTdIfNeed()
    }
}

export default ManageTd;