import React from 'react';
import { Button } from 'antd';

export default class ControlPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      startButtonShow: true
    };
  }

  render() {
    let startBtn = this.state.startButtonShow;
    return (
      <div style={styles.mainContainer}>
        <div style={styles.container}>
          <Button shape="circle" icon="left" onClick={this.clickBtn('left')} />
          <Button
            shape="circle"
            icon={startBtn ? 'caret-right' : 'pause'}
            style={{ height: 60, width: 60, marginLeft: 25, marginRight: 25 }}
            onClick={this.clickBtn('center')}
          />
          <Button shape="circle" icon="right" onClick={this.clickBtn('right')} />
        </div>
        <a onClick={this.clickBtn('reset')}>重置</a>
      </div>
    );
  }

  clickBtn = which => () => {
    let control = this.props.getGameControl();
    if (which === 'center') {
      if (this.state.startButtonShow) {
        control.start();
      } else {
        control.pause();
      }
      this.setState(({ startButtonShow }) => ({ startButtonShow: !startButtonShow }));
    } else if (which === 'right') {
      control.next();
    } else if (which === 'left') {
      control.prev();
    } else if (which === 'reset') {
      control.reset();
    }
  };
}

const styles = {
  mainContainer: {
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center'
  },
  container: {
    marginBottom: 20,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center'
  }
};
