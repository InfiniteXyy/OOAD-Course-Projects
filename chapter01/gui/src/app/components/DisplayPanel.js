import React from 'react';
import { themeColor } from '../global/colors';
import Anime from 'react-anime';
import propTypes from 'prop-types';

const updateInterval = 600;
const stickViewWidth = 500;

export default class DisplayPanel extends React.Component {
  static propTypes = {
    stickLength: propTypes.number,
    bind: propTypes.func
  };

  static defaultProps = {
    stickLength: 500
  };

  constructor (props) {
    super(props);
    this.state = {
      antPositions: [
        {
          index: 1,
          position: 0,
          lastPosition: 0,
          died: false
        }
      ]
    };
    props.bind({ start: this.start, pause: this.pause, next: this.next });
  }

  updatePosition = () => {
    this.setState(({ antPositions }) => {
      let newPositions = [...antPositions];
      for (let i of newPositions) {
        i.lastPosition = i.position;
        i.position += 30;
        if (i.position >= this.props.stickLength) {
          i.died = true;
        }
      }
      return { antPositions: newPositions };
    });
  };

  render () {
    let { position, lastPosition, died } = this.state.antPositions[0];
    return (
      <div style={styles.container}>
        <div style={styles.ballContainer}>
          <Anime
            easing={'linear'}
            duration={updateInterval}
            translateX={[lastPosition, position]}
            opacity={[1, died ? 0 : 1]}
            key={11 + Date.now()}
          >
            {lastPosition >= this.props.stickLength ? <div/> : <div
              style={styles.circle}/>}
          </Anime>
        </div>

        <div style={styles.stick}/>
        <span>
          <b>步数: </b>
          10 / 120
        </span>
        <span>
          <b>存活: </b>5 / 10
        </span>
      </div>
    );
  }

  start = () => {
    this.updatePosition();
    this.intervalId = setInterval(this.updatePosition, updateInterval);
  };
  pause = () => {
    clearInterval(this.intervalId);
    console.log("pause")
  };
  next = () => {
    this.updatePosition();
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
    width: stickViewWidth
  },
  circle: {
    width: 16,
    height: 16,
    borderRadius: '100%',
    background: 'steelblue'
  }
};
