import {API_URL} from "../constants";
import {getToken} from "../utils/tokenStorage";

const ROUTE = `${API_URL}/files`

export default function (file) {
	const token = getToken();
	const formData = new FormData();
	formData.append('file', file);

	const request = new Request(ROUTE, {
		method: 'POST',
		mode: 'cors',
		headers: {
			'Authorization': token
		},
		body: formData
	});


	return new Promise((resolve, reject) => {
		fetch(request)
			.then((response) => {
				if(!response.ok) {
					throw response.json()
				}

				return response.json();
			})
			.then((data) => {
				resolve(data);
			})
			.catch((error) => {
				error.then(error => reject(error))
			})
	});
}