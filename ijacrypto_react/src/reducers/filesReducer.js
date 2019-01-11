import {fromJS} from "immutable";
import {FILES_END_LOADING, FILES_FETCH, FILES_LOADING, FILES_UPLOAD} from "../actions/filesActions";

const initialState = fromJS({
	filesOwn: [],
	filesShared: [],
	isLoading: true,
});

export default function (state = initialState, {type, payload}) {
	switch (type) {
		case FILES_FETCH:
			return fromJS(payload);
		case FILES_UPLOAD:
			return state.update('filesOwn', f => f.push(fromJS(payload)))
		case FILES_LOADING:
			return state.set('isLoading', true);
		case FILES_END_LOADING:
			return state.set('isLoading', false);
		default:
			return state;
	}
}