import React from 'react';
import PropTypes from 'prop-types'
import Button from "@material-ui/core/Button";
import CloudDownloadIcon from "../../node_modules/@material-ui/icons/CloudDownload";
import downloadFileService from "../services/downloadFileService";
import fileDownload from 'js-file-download'

const propTypes = {
	file: PropTypes.object.isRequired,
}

class DownloadFileButton extends React.PureComponent {
	handleClick = event => {
		downloadFileService(this.props.file.id)
			.then((fileBlob) => fileDownload(fileBlob, this.props.file.name))
			.catch(error => console.log(error))
	}

	render() {
		return (
				<Button size={"small"} mini={true} onClick={this.handleClick}>
					<CloudDownloadIcon titleAccess={"Download"} />
				</Button>
		)
	}
}

DownloadFileButton.propTypes = propTypes;

export default DownloadFileButton