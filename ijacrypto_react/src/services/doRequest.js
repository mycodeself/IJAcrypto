import {requestConfig} from "../constants";

export default function (method, route, body = null) {
	let initRequest = {
		...requestConfig,
		method: method,
	};

	if(body) {
		initRequest.body = JSON.stringify(body);
	}

	const request = new Request(route, initRequest);

	return new Promise((resolve, reject) => {
		fetch(request)
			.then(response => {
				if(!response.ok) {
					throw response.json()
				}

				return response.json();
			})
			.then(() => {
				resolve();
			})
			.catch(error => {
				error.then(err => reject(err))
			})
	});
}