import doAuthorizedRequest from "./doAuthorizedRequest";
import {API_URL} from "../constants";

export default function () {
	const route = `${API_URL}/files`;

	return doAuthorizedRequest('GET', route);
}