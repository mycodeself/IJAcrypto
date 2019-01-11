export const ENQUEUE_SNACKBAR = 'ENQUEUE_SNACKBAR';
export const REMOVE_SNACKBAR = 'REMOVE_SNACKBAR';

export const enqueueSnackbar = notification => ({
	type: ENQUEUE_SNACKBAR,
	payload: {
		key: new Date().getTime() + Math.random(),
		...notification,
	},
});

export const removeSnackbar = key => ({
	type: REMOVE_SNACKBAR,
	payload: key,
});


export const notifySuccess = (message) => {
	return dispatch => {
		dispatch(enqueueSnackbar({
			message: message,
			options: {
				variant: 'success',
			},
		}))
	}
};

export const notifyError = (message) => {
	return dispatch => {
		dispatch(enqueueSnackbar({
			message: message,
			options: {
				variant: 'error',
			},
		}))
	}
};

export const notifyWarning = (message) => {
	return dispatch => {
		dispatch(enqueueSnackbar({
			message: message,
			options: {
				variant: 'warning',
			},
		}))
	}
};

export const notifyInfo = (message) => {
	return dispatch => {
		dispatch(enqueueSnackbar({
			message: message,
			options: {
				variant: 'info',
			},
		}))
	}
}