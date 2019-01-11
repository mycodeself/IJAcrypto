import React from 'react';
import PropTypes from 'prop-types'
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';

const propTypes = {
	onSubmit: PropTypes.func.isRequired,
	buttonText: PropTypes.string.isRequired,
	buttonColor: PropTypes.string.isRequired
}

class LoginForm extends React.PureComponent {
	constructor(props) {
		super(props);

		this.state = {
			email: '',
			password: ''
		}
	}

	handleChange = (event) => {
		this.setState({[event.target.name]: event.target.value})
	}

	handleSubmit = (event) => {
		event.preventDefault();
		this.props.onSubmit(this.state);
	}

	render() {
		return (
			<form onSubmit={this.handleSubmit}>
				<Grid
					container
					alignItems="center"
					justify="center"
					direction="column"
				>
					<TextField
						id="email"
						name="email"
						label="E-mail"
						type="email"
						autoComplete="off"
						margin="normal"
						variant="outlined"
						value={this.state.email}
						onChange={this.handleChange}
						required
					/>
					<TextField
						id="password"
						name="password"
						label="Password"
						type="password"
						autoComplete="current-password"
						margin="normal"
						variant="outlined"
						value={this.state.password}
						onChange={this.handleChange}
						required
					/>
					<Grid>
						<Button
							type="submit"
							variant="contained"
							color={this.props.buttonColor}
							size="large"
							style={{margin: '1em'}}
						>
							{this.props.buttonText}
						</Button>
					</Grid>
				</Grid>
			</form>
		)
	}
}

LoginForm.propTypes = propTypes;

export default LoginForm