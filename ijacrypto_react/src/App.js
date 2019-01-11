import React, {Component} from 'react';
import {Grid} from '@material-ui/core';
import MainAppBar from "./components/MainAppBar";
import connect from "react-redux/es/connect/connect";
import PublicView from "./components/PublicView";
import PrivateView from "./components/PrivateView";
import {logout} from "./actions/authActions";

class App extends Component {
  render() {
    return (
      <Grid>
				<MainAppBar
					logout={this.props.logout}
					isLoggedIn={this.props.isLoggedIn}
				/>
	      <Grid
		      container
		      justify="center"
		      alignItems="center"
		      style={{padding: '1em'}}
	      >
		      {
			      this.props.isLoggedIn
			        ? <PrivateView/>
				      : <PublicView/>
		      }
	      </Grid>
      </Grid>
    );
  }
}

const mapStateToProps = (state, ownState) => {
	return {
		isLoggedIn: state.getIn(['auth', 'isLoggedIn']),
	}
};

const mapDispatchToProps = (dispatch) => {
	return {
		logout: () => dispatch(logout())
	}
};

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(App);
