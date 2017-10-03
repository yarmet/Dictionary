import {createStore} from 'redux'
import combineReducers from './reducers/index';


export default createStore(combineReducers, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());