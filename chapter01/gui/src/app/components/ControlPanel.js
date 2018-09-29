import React from 'react';
import { Button } from 'antd';
import propTypes from 'prop-types';

export default class ControlPanel extends React.Component {
  static propTypes = {
    methods: propTypes.shape({
      start: propTypes.func,
      pause: propTypes.func,
      next: propTypes.func,
      prev: propTypes.func
    }).isRequired
  };

  constructor(props) {
    super(props);
    this.state = {
      startButtonShow: true
    };
  }

  render() {
    let startBtn = this.state.startButtonShow;
    return (
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
    );
  }

  clickBtn = which => () => {
    if (which === 'center') {
      if (this.state.startButtonShow) {
        this.props.methods.start();
      } else {
        this.props.methods.pause();
      }
      this.setState(({ startButtonShow }) => ({ startButtonShow: !startButtonShow }));
    } else if (which === 'right') {
      this.props.methods.next();
    } else if (which === 'left') {
      this.props.methods.prev();
    }
  };
}

const styles = {
  container: {
    height: '100%',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center'
  }
};
