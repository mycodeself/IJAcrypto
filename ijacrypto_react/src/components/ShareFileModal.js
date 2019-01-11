import React from 'react'
import PropTypes from 'prop-types'
import Button from "@material-ui/core/Button";
import ShareIcon from "../../node_modules/@material-ui/icons/Share";
import Modal from '@material-ui/core/Modal';
import Paper from "@material-ui/core/Paper/Paper";
import UsersList from "./UsersList";
import Typography from "@material-ui/core/Typography/Typography";

const propTypes = {
	file: PropTypes.object.isRequired,
}

class ShareFileModal extends React.PureComponent {
	constructor(props) {
		super(props);

		this.state = {
			isOpen: false,
			users: [],
		}
	}

	handleOpen = () => {
		this.setState({isOpen: true})
	}

	handleClose = () => {
		this.setState({isOpen: false, users: []});
	}

	render() {
		return (
			<React.Fragment>
				<Button size={"small"} mini={true} onClick={this.handleOpen}>
					<ShareIcon titleAccess={"Share"} />
				</Button>
				<Modal
					open={this.state.isOpen}
					onClose={this.handleClose}
				>
					<Paper style={{width: 310, marginRight: 'auto', marginLeft: 'auto', marginTop: '15%'}}>
						<header style={{display: 'flex', textAlign: 'center', flexDirection: 'column', padding: '1em'}}>
							<Typography variant={"title"}>
								Share permissions
							</Typography>
							<Typography variant={"body2"}>
								{this.props.file.name}
							</Typography>
						</header>
						<Typography align={"center"} variant={"body1"}>
							Select the users to grant permissions
						</Typography>
						<UsersList file={this.props.file} />
						<div style={{padding: '1em', textAlign: 'center'}}>
							<Button variant={"text"} onClick={this.handleClose}>
								Close
							</Button>
						</div>
					</Paper>
				</Modal>
			</React.Fragment>
		)
	}
}

ShareFileModal.propTypes = propTypes;

export default ShareFileModal