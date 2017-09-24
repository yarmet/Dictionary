import React, {Component} from 'react';


class RadioBlock extends Component {
    constructor(props) {
        super(props);
        this.state = {selected: this.props.deffCheck};
        this.click = this.click.bind(this);
    }

    click(e) {
        var selectValue = e.target.value;
        this.setState({selected: selectValue});
        this.props.callback(selectValue);
    }

    render() {
        return <div className="group1">
            {
                this.props.values.map(function (row, id) {
                    return <label key={id}><input type="radio" name={this.props.name}
                                                  value={row}
                                                  checked={row === this.state.selected}
                                                  onChange={this.click}/>{row}</label>
                }, this)
            }
        </div>
    }
}

export default RadioBlock;