import React, { Component } from 'react';
import SettingPanel from './SettingPanel';
import MainPanel from './MainPanel';
import 'antd/dist/antd.css';
import { fetchTest } from './global/utils';

export default class App extends Component {
  constructor(props) {
    super(props);
    fetchTest(result => {
      console.log(result)
    })
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
