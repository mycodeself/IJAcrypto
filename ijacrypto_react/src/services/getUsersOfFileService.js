import {API_URL} from "../constants";
import doAuthorizedRequest from "./doAuthorizedRequest";

export default function (fileId) {
	const route = `${API_URL}/files/${fileId}/users`;

	return doAuthorizedRequest('GET', route);
}