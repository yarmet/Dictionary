import React, {Component} from 'react';
import {connect} from 'react-redux';
import LoadButton from "./LoadButton";
import LanguageSwitcher from "./radio/LanguageSwitcher";
import LoadChoiceSwitcher from "./radio/loadChoiceSwitcher";
import HiddenTD from "./HiddenTD";
import ManageTD from "./ManageTD";
import AddBlock from "./AddBlock";
import EditBlock from "./EditBlock";
import RemoveBlock from './RemoveBlock';
import StoreCommands from "./store/storecommands";
import Navbar from "./Navbar";


class Page extends Component {

    constructor(props) {
        super(props);

        this.counter = 0;

        this.state = {
            hideRusLang: true,
            loadAll: true,
            tableBlocked: false,
            admin: false,
            editBlock: {show: false},
            addBlock: {show: false},
            removeBlock: {show: false}
        };
    }


    render() {

        {
            this.counter += this.props.elements.length

            if (this.state.tableBlocked) {
                document.body.classList.add('transparent');
            } else {
                document.body.classList.remove('transparent');
            }
        }

        return (
            <div>

                <Navbar callback={() => this.setState({admin: !this.state.admin})}/>

                <h3>Словарь</h3>

                <LoadChoiceSwitcher initValue={this.state.loadAll}
                                    action={bool => this.setState({loadAll: bool})}/>

                <LanguageSwitcher initValue={this.state.hideRusLang}
                                  action={bool => this.setState({hideRusLang: bool})}/>

                <AddBlock close={() => this.setState({tableBlocked: false, addBlock: {show: false}})}
                          show={this.state.addBlock.show} action={this.props.addRow}/>

                <EditBlock close={() => this.setState({tableBlocked: false, editBlock: {show: false}})}
                           values={this.state.editBlock} action={this.props.updateRow}/>

                <RemoveBlock close={() => this.setState({tableBlocked: false, removeBlock: {show: false}})}
                             values={this.state.removeBlock} action={this.props.removeRow}/>

                <LoadButton action={this.props.updateAll} loadAll={this.state.loadAll}/>

                <table className="shadow">

                    <thead>
                    <tr>
                        <td> {this.state.hideRusLang ? 'английский' : 'русский'}</td>
                        <td> {this.state.hideRusLang ? 'русский' : 'английский'}</td>

                        {this.state.admin ?
                            <td colSpan="2"> {this.state.tableBlocked ? <span>добавить слово</span> :
                                <a href="javascript:void(0);"
                                   onClick={() => this.setState({addBlock: {show: true}, tableBlocked: true})}>добавить
                                    слово</a>} </td> : null}
                    </tr>
                    </thead>

                    <tbody>

                    {this.props.elements.map((row, arrId) => {
                        return <tr key={this.counter + arrId}>

                            <td>{this.state.hideRusLang ? row.english : row.russian}</td>

                            <HiddenTD>{this.state.hideRusLang ? row.russian : row.english}</HiddenTD>


                            <ManageTD blocked={this.state.tableBlocked} arrayId={arrId} admin={this.state.admin}
                                      row={row} callBack={(row, arrID) =>
                                this.setState({
                                    tableBlocked: true,
                                    editBlock: {show: true, arrayId: arrID, row: row}
                                })}>ред.</ManageTD>


                            <ManageTD blocked={this.state.tableBlocked} arrayId={arrId} admin={this.state.admin}
                                      row={row} callBack={(row, arrID) =>
                                this.setState({
                                    tableBlocked: true,
                                    removeBlock: {show: true, arrayId: arrID, id: row.id}
                                })
                            }>уд.</ManageTD>

                        </tr>
                    })}
                    </tbody>
                </table>
            </div>
        );
    }
}


export default connect(
    state => ({elements: state.rows}),
    dispatch => ({
        addRow: row => dispatch({type: StoreCommands.ADD_ROW, payload: row}),
        updateAll: arr => dispatch({type: StoreCommands.UPDATE_ALL_ROWS, payload: arr}),
        updateRow: (arrID, row) => dispatch({type: StoreCommands.UPDATE_ROW, id: arrID, payload: row}),
        removeRow: arrayId => dispatch({type: StoreCommands.DELETE_ROW, id: arrayId}),
        addRow: row => dispatch({type: StoreCommands.ADD_ROW, payload: row})
    })
)(Page);
