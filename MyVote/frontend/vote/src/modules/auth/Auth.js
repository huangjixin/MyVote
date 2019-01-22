import React, { Component } from 'react';

class Auth extends Component {

    constructor(props) {
        super(props);
        this.platform = 'PC';

        this.platformToIdentify();

        this.loginAuth();
    }


    //平台识别：是手机端还是PC端
    platformToIdentify = () => {
        if (/Android|webOS|iPhone|iPad|BlackBerry/i.test(navigator.userAgent)) {
            this.platform = 'mobile';
        } else {
            this.platform = 'PC';
        }
    }

    //登录验证
    loginAuth = () => {
        //是否支持localStorage
        if (window.localStorage) {
            let localStorage = window.localStorage;
            let sessionStorage = window.sessionStorage;
            let userInfoStr = localStorage.getItem('userInfo');

            //是否曾经登录过
            if (typeof userInfoStr != 'undefined' && userInfoStr != null && userInfoStr != '' && userInfoStr.length > 0) {
                sessionStorage.setItem('userInfo', userInfoStr);
            }

            this.routeNavigate();
        } else { //不支持localStorage，直接跳到登录页面

            this.routeNavigate();
        }
    }

    routeNavigate = () => {
        if (this.platform == 'PC') {
            let path = {
                pathname: '/pc'
            };
            this.props.history.replace(path);
        } else {
            let path = {
                pathname: '/mobile'
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

export default Auth;