import {fromJS} from "immutable";
import {AUTH_LOGOUT, AUTH_SIGN_IN} from "../actions/authActions";


const initialState = fromJS({
	email: '',
	token: '',
	isLoggedIn: false
})

export default function (state = initialState, {type, payload}) {
	switch (type) {
		case AUTH_SIGN_IN:
			return state
				.set('email', payload.email)
				.set('token', payload.token)
				.set('isLoggedIn', true);
		case AUTH_LOGOUT:
			return state
				.set('email', '')
				.set('token', '')
				.set('isLoggedIn', false);
		default:
			return state;
	}
}