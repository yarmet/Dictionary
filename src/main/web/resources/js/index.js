const app = document.getElementById("programm");
const rootPath = document.getElementById("rootPath").innerText;

var Languages = {RUSSIAN: "русский", ENGLISH: 'english'};
var LoadOptions = {ALL: 'загрузить все', LAST: 'загрузить последние'};


class MyNavbar extends React.Component {

    constructor(props) {
        super(props);
        this.adminModeToggle = this.adminModeToggle.bind(this);
    }

    adminModeToggle() {
        this.props.callback();
    }

    render() {
        return <nav className="navbar navbar-default">
            <div className="container-fluid">
                <div className="nav navbar-nav">
                    <div className="navbar-brand"><a onClick={this.adminModeToggle} href="javascript:void(0);">админка(открыть/закрыть)</a>
                    </div>
                </div>
                <div className="nav navbar-nav navbar-right">
                    <div className="navbar-brand"><a href="<c:url value='/login'/>">войти</a>
                    </div>
                </div>
            </div>
        </nav>
    }
}

//------------------------------------------------------------------------------------------------------------------

class RadioBlock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {selected: this.props.deffCheck};
        this.clickRadio = this.clickRadio.bind(this);
    }

    clickRadio(e) {
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
                                                  onChange={this.clickRadio}/>{row}</label>
                }, this)
            }
        </div>
    }
}

//------------------------------------------------------------------------------------------------------------------

class HiddenTd extends React.Component {

    constructor(props) {
        super(props);
        this.state = ({hidden: true});
        this.click = this.click.bind(this);
    }

    click() {
        this.setState({hidden: false})
    }

    render() {
        return (
            <td> {this.state.hidden ? <a onClick={this.click} href="#">показать</a> : this.props.children} </td>
        )
    }
}

//------------------------------------------------------------------------------------------------------------------

class EditBlock extends React.Component {
    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(this.props.values.arrayId, this.props.values.id, this.refs.rus.value, this.refs.eng.value)
    }

    closeBlock() {
        this.props.callback(null, null, null, null)
    }

    render() {
        var display = null;

        if (this.props.values.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="#">&times;</a></div>
                <input className="form-control" ref="eng" placeholder={Languages.ENGLISH}
                       defaultValue={this.props.values.eng}/>

                <input className="form-control" ref="rus" placeholder={Languages.RUSSIAN}
                       defaultValue={this.props.values.rus}/>

                <button className="btn btn-danger" onClick={this.getResult}>изменить</button>
            </div>
        }
        return display;
    }
}

//------------------------------------------------------------------------------------------------------------------

class RemoveBlock extends React.Component {

    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(true, this.props.values.id, this.props.values.arrayId);
    }

    closeBlock() {
        this.props.callback(false)
    }

    render() {
        var display = null;
        if (this.props.values.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="#">&times;</a></div>
                <button className="btn btn-danger" onClick={this.getResult}>уверены что хотите удалить?</button>
            </div>
        }
        return display;
    }

}

//------------------------------------------------------------------------------------------------------------------

class AddBlock extends React.Component {

    constructor(props) {
        super(props);
        this.getResult = this.getResult.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
    }

    getResult() {
        this.props.callback(this.refs.rus.value, this.refs.eng.value);
    }

    closeBlock() {
        this.props.callback(null, null)
    }

    render() {
        var display = null;
        if (this.props.show) {
            display = <div className="positionAbsolute form-inline">
                <div className="closeSymbol"><a onClick={this.closeBlock} href="#">&times;</a></div>
                <input className="form-control" ref="eng" placeholder={Languages.ENGLISH}/>
                <input className="form-control" ref="rus" placeholder={Languages.RUSSIAN}/>
                <button className="btn btn-danger" onClick={this.getResult}>добавить</button>
            </div>
        }
        return display;
    }
}

//------------------------------------------------------------------------------------------------------------------

class ManageTd extends React.Component {

    constructor(props) {
        super(props);
        this.func = this.func.bind(this);
        this.drawTdIfNeed = this.drawTdIfNeed.bind(this);
        this.drawUrlIfNeed = this.drawUrlIfNeed.bind(this);

    }

    func(row, arrayId) {
        this.props.callBack(row, arrayId);
    }

    drawUrlIfNeed() {
        return this.props.blocked ? <span>{this.props.children}</span> :
            <a onClick={this.func.bind(null, this.props.row, this.props.arrayId)}
               href="#">{this.props.children}</a>
    }

    drawTdIfNeed() {
        return this.props.admin ? <td>{this.drawUrlIfNeed()} </td> : null;
    }

    render() {
        return this.drawTdIfNeed()
    }
}

//------------------------------------------------------------------------------------------------------------------

class Block extends React.Component {

    constructor(props) {
        super(props);


        this.state = {
            rows: [],

            radioValues: [Languages.RUSSIAN, Languages.ENGLISH],
            radioDeffCheck: Languages.ENGLISH,

            loadValues: [LoadOptions.ALL, LoadOptions.LAST],
            loadSelected: LoadOptions.ALL,

            admin: false,

            blocked: false,
            editBlock: {show: false},
            addBlock: {show: false},
            removeBlock: {show: false}
        };

        this.radioResult = this.radioResult.bind(this);
        this.loadMore = this.loadMore.bind(this);

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

    loadMore() {
        ajax(rootPath.concat('/getWords'), 'GET', null).then(function (rows) {
            this.setState({rows: JSON.parse(rows)});
        }.bind(this));
    }

    openEditBlock(row, arrayID) {
        this.setState({
            editBlock: {show: true, arrayId: arrayID, id: row.id, rus: row.russian, eng: row.english},
            blocked: true
        });
    }

    editBlockResult(arrayId, rowId, rus, eng) {
        // если все ок, то
        var arr = this.state.rows;
        arr[arrayId] = {id: rowId, russian: rus, english: eng};
        this.setState({rows: arr, blocked: false, editBlock: {show: false}});
    }


    openAddBlock() {
        this.setState({addBlock: {show: true}, blocked: true});
    }

    openAddBlockResult(rus, eng) {
        if (rus !== null && eng !== null) {
            // если все ок, то
            var arr = this.state.rows;
            arr.push({id: "new id", russian: rus, english: eng});
        }
        this.setState({addBlock: {show: false}, blocked: false});
    }


    openRemoveBlock(row, arrayId) {
        this.setState({removeBlock: {show: true, arrayId: arrayId, id: row.id}, blocked: true})
    }

    openRemoveBlockresult(decide, rowId, arrayId) {
        if (decide) {
            // если все ок
            var arr = this.state.rows;
            arr.splice(arrayId, 1);
        }
        this.setState({blocked: false, removeBlock: {show: false}})
    }

    adminModeToggle() {
        var adm = this.state.admin;
        this.setState({admin: !adm})
    }


    render() {

        if (this.state.blocked) {
            document.body.classList.add('transparent');
        } else {
            document.body.classList.remove('transparent');
        }

        return <div>
            <MyNavbar callback={this.adminModeToggle} admin={this.props.admin}/>

            <h3>словарик</h3>

            <RadioBlock name="loadOpt" values={this.state.loadValues} deffCheck={this.state.loadSelected}
                        callback={this.loadOptionResult}/>

            <RadioBlock name="langChoice" values={this.state.radioValues} deffCheck={this.state.radioDeffCheck}
                        callback={this.radioResult}/>

            <EditBlock callback={this.editBlockResult} values={this.state.editBlock}/>
            <AddBlock callback={this.openAddBlockResult} show={this.state.addBlock.show}/>
            <RemoveBlock callback={this.openRemoveBlockresult} values={this.state.removeBlock}/>

            <div>
                <button className="btn btn-danger" onClick={this.loadMore}>загрузить</button>
            </div>

            <table>
                <thead>
                <tr>
                    <td> {this.state.radioDeffCheck} </td>
                    <td> {this.state.radioDeffCheck === Languages.RUSSIAN ? Languages.ENGLISH : Languages.RUSSIAN} </td>
                    {this.state.admin ?
                        <td colSpan="2"><a href="#" onClick={this.openAddBlock}>добавить слово</a></td> : null}
                </tr>
                </thead>

                <tbody>
                {
                    this.state.rows.map(function (row, arrayID) {
                        return <tr key={row.id}>
                            <td>{this.state.radioDeffCheck === Languages.RUSSIAN ? row.russian : row.english}</td>

                            <HiddenTd>{this.state.radioDeffCheck === Languages.RUSSIAN ? row.english : row.russian}</HiddenTd>

                            <ManageTd admin={this.state.admin} blocked={this.state.blocked} row={row}
                                      arrayId={arrayID} callBack={this.openEditBlock}>ред.</ManageTd>

                            <ManageTd admin={this.state.admin} blocked={this.state.blocked} row={row}
                                      arrayId={arrayID} callBack={this.openRemoveBlock}>уд.</ManageTd>

                        </tr>
                    }, this)
                }
                </tbody>
            </table>
        </div>
    }
}

ReactDOM.render(<Block/>, app);
