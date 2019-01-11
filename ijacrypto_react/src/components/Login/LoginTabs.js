import React from 'react'
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import SignInForm from "./SignInForm";
import SignUpForm from "./SignUpForm";
import Grid from "@material-ui/core/Grid";

class LoginTabs extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			index: 0
		}
	}

	handleChange = (event, index) => {
		this.setState({ index });
	};

	render() {
		return (
			<Grid>
				<AppBar position="static">
					<Tabs value={this.state.index} onChange={this.handleChange}>
						<Tab label="Sign In" />
						<Tab label="Sign Up" />
					</Tabs>
				</AppBar>
				{this.state.index === 0 && <SignInForm/>}
				{this.state.index === 1 && <SignUpForm/>}
			</Grid>
		);
	}
}

export default LoginTabs