import React, {Component} from 'react';


import Languages from "./Languages";
import LoadOptions from "./LoadOptions";
import ajax from "./Ajax";
import Navbar from "./Navbar";
import RadioBlock from "./RadioBlock";
import EditBlock from "./EditBlock";
import AddBlock from "./AddBlock";
import RemoveBlock from "./RemoveBlock";
import HiddenTd from "./HiddenTd";
import ManageTd from "./ManageTd";


class Block extends Component {

    constructor(props) {
        super(props);

        this.state = {
            rows: [],

            radioValues: [Languages.RUSSIAN, Languages.ENGLISH],
            radioDeffCheck: Languages.ENGLISH,

            loadValues: [LoadOptions.ALL, LoadOptions.LAST],
            loadSelected: LoadOptions.ALL,

            admin: false,
            count: 0,

            urlsBlocked: false,
            editBlock: {show: false},
            addBlock: {show: false},
            removeBlock: {show: false}
        };

        this.radioResult = this.radioResult.bind(this);
        this.loadWords = this.loadWords.bind(this);

        this.openEditBlock = this.openEditBlock.bind(this);
        this.editBlockResult = this.editBlockResult.bind(this);

        this.adminModeToggle = this.adminModeToggle.bind(this);

        this.openAddBlock = this.openAddBlock.bind(this);
        this.openAddBlockResult = this.openAddBlockResult.bind(this);

        this.openRemoveBlock = this.openRemoveBlock.bind(this);
        this.openRemoveBlockresult = this.openRemoveBlockresult.bind(this);

        this.loadOptionResult = this.loadOptionResult.bind(this);
    }

    radioResult(value) {
        this.setState({radioDeffCheck: value})
    }

    loadOptionResult(value) {
        this.setState({loadSelected: value})
    }

    componentDidMount() {
        ajax('/getWords', 'GET', null, true).then(function (rows) {
            this.setState({rows: JSON.parse(rows)});
        }.bind(this));
    }

    loadWords() {
        ajax(this.state.loadSelected === LoadOptions.LAST ? '/getLastWords' : '/getWords', 'GET', null, true).then(function (rows) {
            var arr = JSON.parse(rows);
            this.setState({rows: arr, count: this.state.count + arr.length});
        }.bind(this));
    }

    openEditBlock(row, arrayID) {
        this.setState({
            editBlock: {show: true, arrayId: arrayID, id: row.id, rus: row.russian, eng: row.english},
            urlsBlocked: true
        });
    }

    editBlockResult(arrayId, row) {
        if (arrayId === null || row === null) {
            this.setState({urlsBlocked: false, editBlock: {show: false}});
        } else {
            ajax('/editWord', 'POST', JSON.stringify(row), true).then(function (row) {
                var arr = this.state.rows;
                arr[arrayId] = JSON.parse(row);
                this.setState({rows: arr, urlsBlocked: false, editBlock: {show: false}});
            }.bind(this));
        }
    }

    openAddBlock() {
        this.setState({addBlock: {show: true}, urlsBlocked: true});
    }

    openAddBlockResult(rus, eng) {
        if (rus !== null && eng !== null) {
            ajax('/addWord', 'POST', JSON.stringify({russian: rus, english: eng}), true).then(function (row) {
                var arr = this.state.rows;
                row = JSON.parse(row);
                arr.push({id: row.id, russian: row.russian, english: row.english});
                this.setState({rows: arr, urlsBlocked: false, addBlock: {show: false}});
            }.bind(this));
        } else {
            this.setState({addBlock: {show: false}, urlsBlocked: false});
        }
    }


    openRemoveBlock(row, arrayId) {
        this.setState({removeBlock: {show: true, arrayId: arrayId, id: row.id}, urlsBlocked: true})
    }

    openRemoveBlockresult(rowId, arrayId) {
        if (rowId !== null && arrayId !== null) {
            ajax('/deleteWord', 'POST', JSON.stringify({id: rowId}), true).then(function () {
                var arr = this.state.rows;
                arr.splice(arrayId, 1);
                this.setState({urlsBlocked: false, rows: arr, removeBlock: {show: false}})
            }.bind(this));
        } else {
            this.setState({urlsBlocked: false, removeBlock: {show: false}})
        }
    }

    adminModeToggle() {
        this.setState({admin: !this.state.admin})
    }


    render() {
        return <div>
            <Navbar callback={this.adminModeToggle} admin={this.props.admin}/>

            <h3>Словарик</h3>

            <RadioBlock name="loadOpt" values={this.state.loadValues} deffCheck={this.state.loadSelected}
                        callback={this.loadOptionResult}/>

            <RadioBlock name="langChoice" values={this.state.radioValues} deffCheck={this.state.radioDeffCheck}
                        callback={this.radioResult}/>

            <EditBlock callback={this.editBlockResult} values={this.state.editBlock}/>
            <AddBlock callback={this.openAddBlockResult} show={this.state.addBlock.show}/>
            <RemoveBlock callback={this.openRemoveBlockresult} values={this.state.removeBlock}/>

            <div id="loadBtn">
                <button className="btn btn-default" onClick={this.loadWords}>загрузить</button>
            </div>

            <table>
                <thead>
                <tr>
                    <td> {this.state.radioDeffCheck} </td>
                    <td> {this.state.radioDeffCheck === Languages.RUSSIAN ? Languages.ENGLISH : Languages.RUSSIAN} </td>
                    {this.state.admin ?
                        <td colSpan="2"><a href="javascript:void(0);" onClick={this.openAddBlock}>добавить слово</a></td> : null}
                </tr>
                </thead>

                <tbody>
                {
                    this.state.rows.map(function (row, arrayID) {
                        return <tr key={this.state.count + arrayID}>
                            <td>{this.state.radioDeffCheck === Languages.RUSSIAN ? row.russian : row.english}</td>

                            <HiddenTd>{this.state.radioDeffCheck === Languages.RUSSIAN ? row.english : row.russian}</HiddenTd>

                            <ManageTd admin={this.state.admin} blocked={this.state.urlsBlocked} row={row}
                                      arrayId={arrayID} callBack={this.openEditBlock}>ред.</ManageTd>

                            <ManageTd admin={this.state.admin} blocked={this.state.urlsBlocked} row={row}
                                      arrayId={arrayID} callBack={this.openRemoveBlock}>уд.</ManageTd>
                        </tr>
                    }, this)
                }
                </tbody>
            </table>
        </div>
    }
}

export default Block;
