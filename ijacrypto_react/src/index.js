import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux'
import {SnackbarProvider} from "notistack";
import CssBaseline from "@material-ui/core/CssBaseline/CssBaseline";
import MuiThemeProvider from "@material-ui/core/es/styles/MuiThemeProvider";
import * as serviceWorker from './serviceWorker';

import App from './App';
import './index.css'
import store from './store'
import Notifier from "./components/Notifier";
import theme from "./theme";

ReactDOM.render(
    <Provider store={store}>
      <MuiThemeProvider theme={theme}>
        <SnackbarProvider
          maxSnack={5}
          anchorOrigin={{vertical: 'top', horizontal: 'right'}}
        >
          <React.Fragment>
            <CssBaseline />
            <Notifier/>
            <App />
          </React.Fragment>
        </SnackbarProvider>
      </MuiThemeProvider>
    </Provider>
    , 
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
