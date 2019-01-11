import React from 'react';
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import CloudUploadIcon from '@material-ui/icons/CloudUpload'
import connect from "react-redux/es/connect/connect";

import {uploadFile} from "../actions/filesActions";

class FileUploader extends React.PureComponent {
	constructor(props) {
		super(props);

		this.inputRef = React.createRef();

		this.state = {
			file: null
		}
	}

	handleChange = event => {
		this.setState({file: event.target.files[0]})
	}

	handleUpload = () => {
		this.props.uploadFile(this.state.file);
		this.clear();
	}

	clear = () => {
		this.inputRef.value = null;
		this.setState({file: null})
	}

	render() {
		return (
			<Paper style={{padding: '1em', margin: '1em', textAlign: 'center'}}>
				<input
					ref={ref => this.inputRef = ref}
					type={"file"}
					name={"file"}
					onChange={this.handleChange}
				/>
				<Button
					color={"primary"}
					variant="contained"
					size="large"
					style={{margin: '1em'}}
					disabled={this.state.file === null}
					onClick={this.handleUpload}
				>
					Upload
					<CloudUploadIcon />
				</Button>
				<Button
					variant={"outlined"}
					style={{margin: '0.5em'}}
					size={"small"}
					disabled={this.state.file === null}
					onClick={this.clear}
				>
					Clear
				</Button>
			</Paper>
		)
	}
}


const mapStateToProps = (state, ownState) => {
	return {
	}
}

const mapDispatchToProps = (dispatch) => {
	return {
		uploadFile: file => dispatch(uploadFile(file))
	}
}


export default connect(
	mapStateToProps,
	mapDispatchToProps
)(FileUploader)