import React, { Component } from 'react';
import SettingPanel from './SettingPanel';
import MainPanel from './MainPanel';
import 'antd/dist/antd.css';

export default class App extends Component {
  constructor(props) {
    super(props);
    fetch('http://localhost:2616', { headers: { 'Access-Control-Allow-Origin': '*' } }).then(result => {
      console.log('123');
      console.log(result);
    });
  }

  render() {
    return (
      <div style={styles.container}>
        <SettingPanel />
        <MainPanel />
      </div>
    );
  }
}

const styles = {
  container: {
    display: 'flex',
    height: '100vh',
    flexDirection: 'row'
  }
};
