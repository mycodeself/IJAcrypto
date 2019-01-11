import { createMuiTheme } from '@material-ui/core/styles';


const theme = createMuiTheme({
	palette: {
		primary: {
			main: '#0277bd'
		},
		secondary: {
			main: '#EF6C00',
		},
	},
	typography: { useNextVariants: true },
});

export default theme