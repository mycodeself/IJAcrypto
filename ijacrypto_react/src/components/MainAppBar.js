import React from 'react'
import PropTypes from 'prop-types'
import {AppBar, Toolbar, Typography} from "@material-ui/core";
import SecurityIcon from '@material-ui/icons/Security';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Button from "@material-ui/core/Button/Button";

const propTypes = {
	logout: PropTypes.func.isRequired,
	isLoggedIn: PropTypes.bool.isRequired
}

const MainAppBar = props => {
	return (
		<AppBar position="sticky">
			<Toolbar>
				<Typography variant="title" color="inherit">
					IJACrypto
				</Typography>
				<SecurityIcon style={{marginLeft: '0.5em'}}/>
				{
					props.isLoggedIn &&
					<Button
						onClick={props.logout}
						style={{position: 'absolute', marginRight: '1em', right: 0}}
						variant={"outlined"}
						color={"inherit"}
						size={"small"}
					>
						Log out
						<ExitToAppIcon />
					</Button>
				}
			</Toolbar>
		</AppBar>
	)
}

MainAppBar.propTypes = propTypes;

export default MainAppBar