import React from 'react'
import LoginForm from "./LoginForm";
import {signIn} from "../../actions/authActions";
import {connect} from "react-redux";

const SignInForm = (props) => {
	return (
		<LoginForm
			onSubmit={(values) => props.signIn(values.email, values.password)}
		  buttonColor={'primary'}
			buttonText={'Sign In'}
		/>
	)
}

const mapStateToProps = (state, ownState) => {
	return {
	}
}

const mapDispatchToProps = (dispatch) => {
	return {
		signIn: (email, password) => dispatch(signIn(email, password))
	}
}

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(SignInForm)