import {fromJS} from "immutable";
import {ENQUEUE_SNACKBAR, REMOVE_SNACKBAR} from "../actions/notifyActions";

const initialState = fromJS({
	notifications: [],
});

export default (state = initialState, {type, payload}) => {
	switch (type) {
		case ENQUEUE_SNACKBAR:
			return state.update('notifications', n => n.push(fromJS(payload)))
		case REMOVE_SNACKBAR:
			return state.deleteIn(['notifications', state.get('notifications').findIndex(n => n.get('key') === payload)]);
		default:
			return state;
	}
};
