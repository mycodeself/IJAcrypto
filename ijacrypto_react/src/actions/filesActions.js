import uploadFileService from "../services/uploadFileService";
import {notifyError, notifyInfo, notifySuccess} from "./notifyActions";
import getFilesService from "../services/getFilesService";
import revokeShareFileService from "../services/revokeShareFileService";
import shareFileService from "../services/shareFileService";

export const FILES_FETCH = 'FILES_FETCH';
export const FILES_SHARE = 'FILES_SHARE';
export const FILES_UPLOAD = 'FILES_UPLOAD';
export const FILES_LOADING = 'FILES_LOADING';
export const FILES_END_LOADING = 'FILES_END_LOADING';

export function fetchFiles() {
	return dispatch => {
		dispatch(loading());
		getFilesService()
			.then(files => {
				dispatch(fetch(files));
				dispatch(endLoading());
			})
			.catch(error => console.log(error));
	}
}

export function uploadFile(file) {
	return dispatch => {
		uploadFileService(file)
			.then(response => {
				dispatch(upload(response))
				dispatch(notifySuccess(`The file ${file.name} was uploaded successfully`));
			})
			.catch(error => {
				console.log(error)
				dispatch(notifyError('An error occurred when uploading the file, try again'));
			})
	}
}

export function shareFile(fileId, userId) {
	return dispatch => {
		shareFileService(fileId, userId)
			.then(() => dispatch(notifySuccess("The file has been shared with the user")))
			.catch(error => console.log(error))
	}
}

export function revokeShareFile(fileId, userId) {
	return dispatch => {
		revokeShareFileService(fileId, userId)
			.then(() => dispatch(notifyInfo("The file is no longer shared with the user")))
			.catch(error => console.log(error))
	}
}

function upload(file) {
	return {
		type: FILES_UPLOAD,
		payload: file
	}
}

function fetch(files) {
	return {
		type: FILES_FETCH,
		payload: files
	}
}

function loading() {
	return {
		type: FILES_LOADING
	}
}

function endLoading() {
	return {
		type: FILES_END_LOADING
	}
}