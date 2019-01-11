import {loadToken} from "../utils/tokenStorage";
import {API_URL} from "../constants";

export default function (fileId) {
	const token = loadToken();
	const route = `${API_URL}/files/${fileId}/decrypt`

	if(!token) {
		throw new Error('No token found in session storage.')
	}

	let initRequest = {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': token
		},
		crossDomain: true,
		credentials: 'include',
		mode: 'cors',
	}

	const request = new Request(route, initRequest);

	return new Promise((resolve, reject) => {
		fetch(request)
			.then((response) => {
				if(!response.ok) {
					throw response.json()
				}

				return response.blob();
			})
			.then(fileBlob => {
				resolve(fileBlob);
			})
			.catch(error => {
				reject(error)
			})
	});
}