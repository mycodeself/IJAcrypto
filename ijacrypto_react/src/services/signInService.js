import {API_URL} from "../constants";

const route = `${API_URL}/login`;

export default function(email, password) {

	const request = new Request(route, {
		method: 'POST',
		crossDomain: true,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		credentials: 'include',
		mode: 'cors',
		body: JSON.stringify({email, password})
	})

	return new Promise((resolve, reject) => {
		fetch(request)
			.then(response => {

				if(!response.ok) {
					throw response.status;
				}

				const authHeader = response.headers.get('Authorization');
				resolve(authHeader);
			})
			.catch(error => reject(error));
	})
}