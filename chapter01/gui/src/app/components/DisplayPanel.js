import React from 'react';
import { beautifulColors, themeColor } from '../global/colors';
import Anime from 'react-anime';
import propTypes from 'prop-types';

const updateInterval = 120;
const stickViewWidth = 500;

const onBorder = position => position === stickViewWidth || position === 0;

export default class DisplayPanel extends React.Component {
  static propTypes = {
    stickLength: propTypes.number,
    bind: propTypes.func,
    data: propTypes.shape({
      antNumber: propTypes.number,
      startPosition: propTypes.arrayOf(propTypes.number),
      stickLength: propTypes.number,
      antSpeed: propTypes.number,
      maxSteps: propTypes.number,
      minSteps: propTypes.number,
      stages: propTypes.arrayOf(
        propTypes.shape({
          directions: propTypes.arrayOf(propTypes.number),
          steps: propTypes.number,
          positions: propTypes.arrayOf(propTypes.arrayOf(propTypes.number))
        })
      )
    }).isRequired
  };

  constructor(props) {
    super(props);
    this.state = {
      lastStep: 0,
      step: 0
    };

    this.props.bind({ start: this.start, pause: this.pause, next: this.next, prev: this.prev, reset: this.reset });
  }

  updatePosition = () => {
    if (this.state.step >= this.props.data.stages[12].steps) {
      clearInterval(this.intervalId);
      return;
    }
    this.setState({ step: this.state.step + 1, lastStep: this.state.step });
  };
  backPosition = () => {
    if (this.state.step === 0) return;
    this.setState({ step: this.state.step - 1, lastStep: this.state.step });
  };

  render() {
    if (!this.props.data.startPosition) return <div />;

    let step = this.state.step;
    let lastStep = this.state.lastStep;
    let positions = this.props.data.stages[12].positions;
    let stickLength = this.props.data.stickLength;
    if (positions[0] !== this.props.data.startPosition) positions.unshift(this.props.data.startPosition);
    return (
      <div style={styles.container}>
        <div style={styles.ballContainer}>
          {[...Array(this.props.data.antNumber).keys()].map((item, index) => {
            let lastPosition = (positions[lastStep][index] / stickLength) * stickViewWidth;
            let nextPosition = (positions[step][index] / stickLength) * stickViewWidth;
            let ballProp = { ...styles.circle };
            ballProp.background = beautifulColors[index];
            return (
              <Anime
                easing={'linear'}
                duration={updateInterval}
                translateX={[lastPosition, nextPosition]}
                key={index + Date.now()}
                opacity={[onBorder(lastPosition) ? 0 : 1, onBorder(nextPosition) ? 0 : 1]}
              >
                <div style={{ ...ballProp }} />
              </Anime>
            );
          })}
        </div>

        <div style={styles.stick} />
        <span>
          <b>步数: </b>
          {step} / {this.props.data.stages[12].steps}
        </span>
        <span>
          <b>存活: </b>5 / 10
        </span>
      </div>
    );
  }

  start = () => {
    if (this.state.step >= this.props.data.stages[12].steps) return;
    this.updatePosition();
    this.intervalId = setInterval(this.updatePosition, updateInterval);
  };
  pause = () => {
    clearInterval(this.intervalId);
    console.log('pause');
  };
  next = () => {
    this.updatePosition();
  };
  prev = () => {
    this.backPosition();
  };
  reset = () => {
    this.setState({ step: 0, lastStep: 0 });
  };
}

const styles = {
  container: {
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'flex-end',
    alignItems: 'center'
  },
  stick: {
    marginTop: 2,
    borderRadius: 4,
    height: 7,
    width: stickViewWidth,
    marginBottom: 50,
    backgroundColor: themeColor.inactiveIcon
  },
  ballContainer: {
    width: stickViewWidth,
    marginBottom: 20
  },
  circle: {
    position: 'absolute',
    width: 16,
    height: 16,
    borderRadius: '100%'
  }
};
