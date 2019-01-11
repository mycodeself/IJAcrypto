export const API_URL = 'http://localhost:8080';

export const requestConfig = {
	crossDomain: true,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	},
	credentials: 'include',
	mode: 'cors',
}