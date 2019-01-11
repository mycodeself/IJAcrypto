
const TOKEN_NAME = 'token';

export function loadToken() {
	return sessionStorage.getItem(TOKEN_NAME);
}

export function saveToken(token) {
	sessionStorage.setItem(TOKEN_NAME, token);
}

export function clearToken() {
	sessionStorage.removeItem(TOKEN_NAME)
}

export function getToken() {
	const token = loadToken();

	if(!token) {
		throw new Error('No authorization token found')
	}

	return token;
}