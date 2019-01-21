import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import MobileAuth from '../mobile/auth/MobileAuth';
import PCAuth from '../pc/auth/PCAuth';
import Auth from './Auth'
import PCHome from '../pc/home/Home';
import PCLogin from '../pc/login/Login';
import MobileHome from '../mobile/home/Home';
import MobileLogin from '../mobile/login/Login';

class AuthRouter extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    render() {
        return (
            <Router>
                <div style={{ display: 'flex', flex: 1 }}>
                    <Switch>
                        <Route path="/" exact component={Auth} ></Route>
                        <Route path="/pc" exact component={PCAuth} ></Route>
                        <Route path="/mobile" exact component={MobileAuth} ></Route>
                        <Route path="/pc/home" component={PCHome} ></Route>
                        <Route path="/pc/login" component={PCLogin} ></Route>
                        <Route path='/mobile/home' exact component={MobileHome}></Route>
                        <Route path='/mobile/login' exact component={MobileLogin}></Route>
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default AuthRouter;