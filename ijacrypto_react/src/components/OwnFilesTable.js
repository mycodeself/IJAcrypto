import React from 'react'
import PropTypes from 'prop-types'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography'
import DownloadFileButton from "./DownloadFileButton";
import ShareFileModal from "./ShareFileModal";

const propTypes = {
	files: PropTypes.array.isRequired,
};

const OwnFilesTable = props => (
	<Paper style={{margin: '1em', overflowX: 'auto'}}>
		<Typography variant={"h4"} align={"center"} style={{margin: '0.5em'}}>
			Your files
		</Typography>
		{
			props.files.length > 0 ?
				<Table>
					<TableHead>
						<TableRow>
							<TableCell>Name</TableCell>
							<TableCell>SHA3 Checksum</TableCell>
							<TableCell>Mime-Type</TableCell>
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
										<ShareFileModal file={file} />
										<DownloadFileButton file={file} />
									</TableCell>
								</TableRow>
							))
						}
					</TableBody>
				</Table>
				:
				<Typography variant={"subtitle1"} align={"center"} style={{margin: '1em'}}>
					You do not have files. Upload a file and easily protect it!
				</Typography>
		}

	</Paper>
);

OwnFilesTable.propTypes = propTypes;

export default OwnFilesTable