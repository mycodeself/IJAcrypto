import React from 'react'
import PropTypes from 'prop-types'
import {MoonLoader} from "react-spinners";
import Typography from "@material-ui/core/Typography/Typography";
import connect from "react-redux/es/connect/connect";

import OwnFilesTable from "./OwnFilesTable";
import SharedFilesTable from "./SharedFilesTable";
import FileUploader from "./FileUploader";
import {fetchFiles} from "../actions/filesActions";

const propTypes = {
	email: PropTypes.string,
	filesOwn: PropTypes.array,
	filesShared: PropTypes.array,
	fetchFiles: PropTypes.func,
}

class PrivateView extends React.PureComponent {
	componentDidMount() {
		this.props.fetchFiles();
	}

	render() {
		return (
			<div style={{width: '100%'}}>
				<Typography style={{margin: '1em'}} align={"center"} variant={"h4"}>
					Welcome {this.props.email}
				</Typography>
				<FileUploader/>
				{
					this.props.isLoading
					?
						<MoonLoader />
					:
						<React.Fragment>
							<OwnFilesTable files={this.props.filesOwn}/>
							<SharedFilesTable files={this.props.filesShared}/>
						</React.Fragment>
				}
			</div>
		)
	}
}

PrivateView.propTypes = propTypes;

const mapStateToProps = store => ({
	isLoading: store.getIn(['files', 'isLoading']),
	filesOwn: store.getIn(['files', 'filesOwn']).toJS(),
	filesShared: store.getIn(['files', 'filesShared']).toJS(),
	email: store.getIn(['auth', 'email'])
});

const mapDispatchToProps = dispatch => ({
	fetchFiles: () => dispatch(fetchFiles())
});

export default connect(
	mapStateToProps,
	mapDispatchToProps
)(PrivateView)