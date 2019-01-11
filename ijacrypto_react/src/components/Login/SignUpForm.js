import React from 'react'
import { connect } from 'react-redux';

import LoginForm from "./LoginForm";
import {signUp} from "../../actions/authActions";

const SignUpForm = props => {
	return (
		<LoginForm
			onSubmit={(values) => props.signUp(values.email, values.password)}
			buttonColor={'secondary'}
			buttonText={'Sign Up'}
		/>
	)
}

const mapStateToProps = (state, ownState) => {
	return {
	}
}

const mapDispatchToProps = (dispatch) => {
	return {
		signUp: (email, password) => dispatch(signUp(email, password))
	}
}

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(SignUpForm)