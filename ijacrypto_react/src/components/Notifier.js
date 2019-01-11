import React from 'react';
import {connect} from 'react-redux';
import {withSnackbar} from 'notistack';
import {removeSnackbar} from "../actions/notifyActions";

class Notifier extends React.Component {
	constructor(props) {
		super(props);

		this.displayed = [];
	}

	storeDisplayed = (key) => {
		this.displayed.push(key);
	};

	render() {
		const { notifications, enqueueSnackbar, removeSnackbar } = this.props;

		notifications.forEach((notification) => {
			setTimeout(() => {
				// If notification already displayed, abort
				if (this.displayed.indexOf(notification.key) > -1) return;
				// Display notification using notistack
				enqueueSnackbar(notification.message, notification.options);
				// Add notification's key to the local state
				this.storeDisplayed(notification.key);
				// Dispatch action to remove the notification from the redux store
				removeSnackbar(notification.key);
			}, 1);
		});

		return null;
	}
}

const mapStateToProps = store => ({
	notifications: store.getIn(['notify', 'notifications']).toJS()
});

const mapDispatchToProps = dispatch => ({
	removeSnackbar: key => dispatch(removeSnackbar(key))
});

export default connect(
	mapStateToProps,
	mapDispatchToProps,
)(withSnackbar(Notifier));
