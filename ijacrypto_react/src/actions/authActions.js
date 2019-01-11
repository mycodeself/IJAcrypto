import signUpService from "../services/signUpService";
import {notifyError, notifySuccess} from "./notifyActions";
import signInService from "../services/signInService";
import {saveToken} from "../utils/tokenStorage";

export const AUTH_SIGN_IN = 'AUTH_SIGN_IN';
export const AUTH_SIGN_UP = 'AUTH_SIGN_UP';
export const AUTH_LOGOUT = 'AUTH_LOGOUT';

export function signIn(email, password) {
	return dispatch => {
		signInService(email, password)
			.then(token => {
				saveToken(token);
				dispatch(notifySuccess('Logged in!'));
				dispatch({
					type: AUTH_SIGN_IN,
					payload: {
						email: email,
						token: token,
					}
				})
			})
			.catch(error => {
				if(error === 403) {
					dispatch(notifyError('Invalid credentials, try again.'))
				} else {
					dispatch(notifyError(error))
				}
			})
	}
}

export function signUp(email, password) {
	return dispatch => {
		signUpService(email, password)
			.then(() => {
				dispatch(notifySuccess('The user was created successfully.'));
				dispatch(signIn(email, password));
				dispatch({type: AUTH_SIGN_UP})
			})
			.catch(error => {
				if(error.hasOwnProperty('message')) {
					dispatch(notifyError(error.message))
				} else {
					dispatch(notifyError('An error has occurred, try again'))
				}
			})
	}
}

export function logout() {
	return {
		type: AUTH_LOGOUT
	}
}