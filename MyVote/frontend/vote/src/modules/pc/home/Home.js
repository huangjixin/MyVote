import React, { Component } from 'react';

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {};
        console.log('pc --- home')
    }
    render() {
        return (
            <div>
                <h1>this is pc Home</h1>
            </div>
        );
    }
}

export default Home;