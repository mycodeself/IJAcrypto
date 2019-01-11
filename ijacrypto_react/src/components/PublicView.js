import React from 'react'
import Paper from "@material-ui/core/Paper";
import LoginTabs from "./Login/LoginTabs";

const PublicView = props => (
	<Paper style={{width: 300}}>
		<LoginTabs />
	</Paper>
)

export default PublicView