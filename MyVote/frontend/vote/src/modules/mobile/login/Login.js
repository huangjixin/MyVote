import React, { Component } from 'react';
import { Card } from 'antd-mobile';
import 'antd-mobile/dist/antd-mobile.css';
import './Login.css';
import {
    Form, Icon, Input, Button, Checkbox,
} from 'antd';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {

        };
    }



    render() {
        return (
            <div style={{
                display: 'flex',
                flex: 1,
                flexDirection: 'column',
                padding: 10,
                justifyContent: 'center',
                alignItems: 'center'
            }}>
                <Card style={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center'
                }}>
                    <Card.Body style={{
                        display: 'flex',
                        flex: 1,
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'flex-end'
                    }}>
                        <Form>
                            <Form.Item>
                                <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
                            </Form.Item>
                            <Form.Item>
                                <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
                            </Form.Item>
                            <Form.Item>
                                <Button type="primary" htmlType="submit" style={{ backgroundColor: 'red', width: '100%' }}>
                                    Log in
                                </Button>
                            </Form.Item>
                        </Form>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}

export default Login;