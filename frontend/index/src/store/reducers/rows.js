import StoreCommands from '../storecommands';




export default function rows(state = [], action) {
    switch (action.type) {
        case StoreCommands.ADD_ROW : {
            return [...state, action.payload]
        }
        case StoreCommands.DELETE_ROW : {
            return [...state.slice(0, action.id), ...state.slice(action.id + 1)]
        }
        case StoreCommands.UPDATE_ROW : {
            return state.map((el, id) => {
                return id === action.id ? action.payload : el
            })
        }
        case StoreCommands.UPDATE_ALL_ROWS: {
            return action.payload;
        }
        default : {
            return state;
        }
    }
}

