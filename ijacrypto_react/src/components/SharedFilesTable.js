import React from 'react'
import PropTypes from 'prop-types'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from "@material-ui/core/Button";
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
import Typography from '@material-ui/core/Typography'
import DownloadFileButton from "./DownloadFileButton";

const propTypes = {
	files: PropTypes.array.isRequired,
};

const SharedFilesTable = props => (
	<Paper style={{margin: '1em', overflowX: 'auto'}}>
		<Typography variant={"h4"} align={"center"} style={{margin: '0.5em'}}>
			Files shared with you
		</Typography>
		{
			props.files.length <= 0
			?
				<Typography variant={"subtitle1"} align={"center"} style={{margin: '1em'}}>
					Nobody has shared any file with you
				</Typography>
			:
				<Table>
					<TableHead>
						<TableRow>
							<TableCell>Name</TableCell>
							<TableCell>Checksum</TableCell>
							<TableCell>Mime-Type</TableCell>
							<TableCell>Owner</TableCell>
							<TableCell></TableCell>
						</TableRow>
					</TableHead>
					<TableBody>
						{
							props.files.map(file => (
								<TableRow key={file.id}>
									<TableCell component="th" scope="row">
										{file.name}
									</TableCell>
									<TableCell>
										{file.checksum}
									</TableCell>
									<TableCell>
										{file.mimeType}
									</TableCell>
									<TableCell>
										{file.owner}
									</TableCell>
									<TableCell>
										<DownloadFileButton file={file} />
									</TableCell>
								</TableRow>
							))
						}
					</TableBody>
				</Table>
		}
	</Paper>
);

SharedFilesTable.propTypes = propTypes;

export default SharedFilesTable