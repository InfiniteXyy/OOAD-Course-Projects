import React, { Component } from 'react';
import SettingPanel from './SettingPanel';
import MainPanel from './MainPanel';
import 'antd/dist/antd.css';

export default class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div style={styles.container}>
        <SettingPanel gameControl={{ start: this.startGame, stop: this.stopGame }} />
        <MainPanel
          bind={i => {
            this.mainPanel = i;
          }}
        />
      </div>
    );
  }

  startGame = config => {
    this.mainPanel.startGame(config);
  };
  stopGame = () => {
    this.mainPanel.stopGame();
  };
}

const styles = {
  container: {
    display: 'flex',
    height: '100vh',
    flexDirection: 'row'
  }
};
