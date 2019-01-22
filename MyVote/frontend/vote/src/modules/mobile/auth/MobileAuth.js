import React, { Component } from 'react';

class MobileAuth extends Component {

    constructor(props) {
        super(props);

        this.mobileLoginAuth();
    }

    mobileLoginAuth = () => {
        if (window.sessionStorage) {
            let sessionStorage = window.sessionStorage;
            let userInfoStr = sessionStorage.getItem('userInfo');
            if (typeof userInfoStr != 'undefined' && userInfoStr != null && userInfoStr != '' && userInfoStr.length > 0) {
                let path = {
                    pathname: '/mobile/home'
                };
                this.props.history.replace(path);
            } else {
                let path = {
                    pathname: '/mobile/login'
                };
                this.props.history.replace(path);
            }
        } else {
            let path = {
                pathname: '/mobile/login'
            };
            this.props.history.replace(path);
        }
    }
    
    render() {
        return (
            <div style={{ display: 'flex', flex: 1 }}></div>
        );
    }
}

export default MobileAuth;