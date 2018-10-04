import React from 'react';
import ControlPanel from './components/ControlPanel';
import DisplayPanel from './components/DisplayPanel';
import { fetchTest } from './global/utils';
import Spin from 'antd/es/spin';

export default class MainPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isStart: false,
      isFetching: false,
      resultSet: {}
    };
    this.props.bind(this);
  }

  render() {
    if (!this.state.isStart) {
      return (
        <div style={styles.fullScreen}>
          <h1 style={{ alignSelf: 'center' }}>请先设置数据</h1>
        </div>
      );
    }
    if (this.state.isFetching) {
      return (
        <div style={styles.fullScreen}>
          <Spin size="large" />
        </div>
      );
    }
    return (
      <div style={styles.container}>
        <div style={{ flex: 1, display: 'flex', justifyContent: 'center' }}>
          <p style={styles.title}>蚂蚁模拟器</p>
        </div>
        <div style={{ flex: 3 }}>
          <DisplayPanel
            data={this.state.resultSet}
            bind={i => {
              this.display = i;
            }}
          />
        </div>
        <div style={{ flex: 2 }}>
          <ControlPanel getGameControl={this.getGameControl} />
        </div>
      </div>
    );
  }

  getGameControl = () => {
    let display = this.display;
    return {
      start: display.start,
      pause: display.pause,
      next: display.next,
      prev: display.prev,
      reset: display.reset
    };
  };

  startGame = config => {
    this.setState({
      isStart: true,
      isFetching: true
    });
    fetchTest(config)
      .then(result => {
        this.setState({ isFetching: false, resultSet: result });
      })
      .catch(e => {
        console.log(e);
        this.setState({ isFetching: false, isStart: true });
      });
  };

  stopGame = () => {
    this.setState({
      isStart: false,
      isFetching: false
    });
  };
}

const styles = {
  fullScreen: {
    display: 'flex',
    flex: '1',
    alignItems: 'center',
    justifyContent: 'center'
  },
  container: {
    height: '100vh',
    display: 'flex',
    flex: 1,
    flexDirection: 'column'
  },
  title: {
    marginTop: 20,
    fontWeight: '500',
    fontSize: 30,
    alignSelf: 'flex-end'
  }
};
