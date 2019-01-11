import {API_URL} from "../constants";
import doAuthorizedRequest from "./doAuthorizedRequest";

export default function (fileId, userId) {
	const route = `${API_URL}/files/${fileId}/share/${userId}`

	return doAuthorizedRequest('POST', route);
}