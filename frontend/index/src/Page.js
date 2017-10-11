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

            showEditBlock: false,
            showAddBlock: false,
            showRemoveBlock: false,

            rowToDispatch: {idInArray: null, row: null}
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


                <LoadChoiceSwitcher initValue={this.state.loadAll} action={bool => this.setState({loadAll: bool})}/>


                <LanguageSwitcher initValue={this.state.hideRusLang}
                                  action={bool => this.setState({hideRusLang: bool})}/>


                {this.state.showAddBlock ?
                    <AddBlock close={() => this.setState({tableBlocked: false, showAddBlock: false})}
                              callback={this.props.addRow}/> : null}


                {this.state.showEditBlock ?
                    <EditBlock close={() => this.setState({tableBlocked: false, showEditBlock: false})}
                               values={this.state.rowToDispatch} callback={this.props.updateRow}/> : null}


                {this.state.showRemoveBlock ?
                    <RemoveBlock close={() => this.setState({tableBlocked: false, showRemoveBlock: false})}
                                 values={this.state.rowToDispatch} callback={this.props.removeRow}/> : null}


                <LoadButton action={this.props.updateAll} loadAll={this.state.loadAll}/>


                <table className="shadow">

                    <thead>
                    <tr>
                        <td> {this.state.hideRusLang ? 'английский' : 'русский'}</td>
                        <td> {this.state.hideRusLang ? 'русский' : 'английский'}</td>


                        {this.state.admin ?
                            <td colSpan="2"> {this.state.tableBlocked ? <span>добавить слово</span> :
                                <a href="javascript:void(0);"
                                   onClick={() => this.setState({showAddBlock: true, tableBlocked: true})}>добавить
                                    слово</a>} </td> : null}
                    </tr>
                    </thead>

                    <tbody>

                    {this.props.elements.map((row, arrId) => {
                        return <tr key={this.counter + arrId}>

                            <td>{this.state.hideRusLang ? row.english : row.russian}</td>


                            <HiddenTD>{this.state.hideRusLang ? row.russian : row.english}</HiddenTD>


                            <ManageTD blocked={this.state.tableBlocked} admin={this.state.admin}
                                      callBack={() =>
                                          this.setState({
                                              tableBlocked: true, showEditBlock: true,
                                              rowToDispatch: {idInArray: arrId, row: row}
                                          })}>ред.</ManageTD>


                            <ManageTD blocked={this.state.tableBlocked} admin={this.state.admin}
                                      callBack={() =>
                                          this.setState({
                                              tableBlocked: true, showRemoveBlock: true,
                                              rowToDispatch: {idInArray: arrId, row: row}
                                          })}>уд.</ManageTD>
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
        removeRow: arrayId => dispatch({type: StoreCommands.DELETE_ROW, id: arrayId})
    })
)(Page);
