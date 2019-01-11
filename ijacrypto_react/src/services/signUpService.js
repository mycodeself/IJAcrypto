import {API_URL} from "../constants";
import doRequest from "./doRequest";

export default function(email, password) {
    const route = `${API_URL}/users`;

    return doRequest('POST', route, {
    	email: email,
	    password: password
    });
}