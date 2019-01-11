import React from 'react';
import PropTypes from 'prop-types';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
import connect from "react-redux/es/connect/connect";

import getUsersOfFileService from "../services/getUsersOfFileService";
import {revokeShareFile, shareFile} from "../actions/filesActions";

const propTypes = {
	file: PropTypes.object,
};

class UsersList extends React.PureComponent {
	constructor(props) {
		super(props);
		this.state = {
			isLoading: false,
			users: [],
		}
	}

	componentDidMount() {
		this.setState({isLoading: true});
		getUsersOfFileService(this.props.file.id)
			.then(users => this.setState({
				users: users,
				isLoading: false,
			}))
			.catch(error => console.log(error))
	}

	handleToggle = userId => () => {
		const users = this.state.users.slice();
		const index = users.findIndex(u => u.id === userId);

		if(this.state.users[index].hasPermission) {
			this.props.revokeShareFile(this.props.file.id, userId);
		} else {
			this.props.shareFile(this.props.file.id, userId);
		}

		users[index].hasPermission = !users[index].hasPermission;
		this.setState({users: users})
	};

	render() {
		return (
			<List>
				{this.state.users.map(user => (
					<ListItem key={user.id} role={undefined} dense button onClick={this.handleToggle(user.id)}>
						<Checkbox
							checked={user.hasPermission}
							tabIndex={-1}
							disableRipple
						/>
						<ListItemText primary={user.email} />
					</ListItem>
				))}
			</List>
		);
	}
}

UsersList.propTypes = propTypes;

const mapStateToProps = store => {
	return {}
}

const mapDispatchToProps = dispatch => {
	return {
		shareFile: (fileId, userId) => dispatch(shareFile(fileId, userId)),
		revokeShareFile: (fileId, userId) => dispatch(revokeShareFile(fileId, userId))
	}
}

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(UsersList);