import React, { Component } from 'react';

class PCAuth extends Component {

    constructor(props) {
        super(props);

        this.pcLoginAuth();
    }

    pcLoginAuth = () => {
        if (window.sessionStorage) {
            let sessionStorage = window.sessionStorage;
            let userInfoStr = sessionStorage.getItem('userInfo');
            if (typeof userInfoStr != 'undefined' && userInfoStr != null && userInfoStr != '' && userInfoStr.length > 0) {
                let path = {
                    pathname: '/pc/home'
                };
                this.props.history.replace(path);
            } else {
                let path = {
                    pathname: '/pc/login'
                };
                this.props.history.replace(path);
            }
        } else {
            let path = {
                pathname: '/pc/login'
            };
            this.props.history.replace(path);
        }
    }

    render() {
        return (
            <div></div>
        );
    }
}

export default PCAuth;