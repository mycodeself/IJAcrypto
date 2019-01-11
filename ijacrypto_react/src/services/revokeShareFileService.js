import doAuthorizedRequest from "./doAuthorizedRequest";
import {API_URL} from "../constants";

export default function (fileId, userId) {
	const route = `${API_URL}/files/${fileId}/share/${userId}`

	return doAuthorizedRequest('DELETE', route);
}