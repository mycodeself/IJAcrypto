import {loadToken} from "../utils/tokenStorage";

export default function (method, route, body = null) {
	const token = loadToken();

	if(!token) {
		throw new Error('No authorized token found in session storage.')
	}

	let initRequest = {
		method: method,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'Authorization': token
		},
		crossDomain: true,
		credentials: 'include',
		mode: 'cors',
	}

	if(body) {
		initRequest.body = JSON.stringify(body);
	}

	const request = new Request(route, initRequest);

	return new Promise((resolve, reject) => {
		fetch(request)
			.then((response) => {
				if(!response.ok) {
					throw response.json()
				}

				return response.json();
			})
			.then(data => {
				resolve(data);
			})
			.catch(error => {
				console.log(error);
				reject(error)
			})
	});
}