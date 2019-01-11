import {combineReducers} from 'redux-immutable'
import auth from './authReducer'
import notify from './notifyReducer'
import files from './filesReducer'

const reducer = combineReducers({
	auth,
	notify,
	files
});

export default reducer