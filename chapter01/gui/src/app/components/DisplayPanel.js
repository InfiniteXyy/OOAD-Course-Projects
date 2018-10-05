import React from 'react';
import { beautifulColors, themeColor } from '../global/colors';
import Anime from 'react-anime';
import { Modal, Button, Table, Popconfirm } from 'antd';
import propTypes from 'prop-types';
import MyPanel from './MyPanel';

const updateInterval = 120;
const stickViewWidth = 500;

const onBorder = position => position >= stickViewWidth || position <= 0;

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
      step: 0,
      stageIndex: 0,
      modalVisible: false
    };

    this.props.bind(this);
  }

  updatePosition = () => {
    if (this.state.step >= this.props.data.stages[this.state.stageIndex].steps) {
      clearInterval(this.intervalId);
      return;
    }
    this.setState({ step: this.state.step + 1, lastStep: this.state.step });
  };
  backPosition = () => {
    if (this.state.step === 0) return;
    this.setState({ step: this.state.step - 1, lastStep: this.state.step });
  };
  showModal = () => {
    this.setState({ modalVisible: true });
  };
  hideModal = () => {
    this.setState({ modalVisible: false });
  };

  render() {
    let stage = this.props.data.stages[this.state.stageIndex];
    let step = this.state.step;
    let lastStep = this.state.lastStep;
    let positions = stage.positions;
    let stickLength = this.props.data.stickLength;
    if (positions[0] !== this.props.data.startPosition) positions.unshift(this.props.data.startPosition);
    return (
      <div style={styles.container}>
        <Button onClick={this.showModal} style={{ marginBottom: 100, marginTop: 20 }}>
          选择方向
        </Button>
        <Modal
          style={{ top: 20 }}
          title="选择一个方向"
          visible={this.state.modalVisible}
          footer={null}
          onCancel={this.hideModal}
        >
          {this.renderStageList()}
        </Modal>
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
                opacity={[onBorder(lastPosition) ? 0 : 0.7, onBorder(nextPosition) ? 0 : 0.7]}
              >
                <div style={{ ...ballProp }} />
              </Anime>
            );
          })}
        </div>
        <div style={styles.stick} />
        <MyPanel panel="步数" content={step + ' / ' + stage.steps} />
        <MyPanel
          panel="存活"
          content={
            stage.positions[step].filter(i => !(i >= stickLength) && !(i <= 0)).length +
            ' / ' +
            stage.positions[0].length
          }
        />
        <MyPanel panel="初始方向" content={stage.directions.map(i => (i === 0 ? '左' : '右')).join(' ')} />
        <MyPanel panel="当前位置" content={stage.positions[step].join(', ')} />
      </div>
    );
  }

  renderStageList = () => {
    return (
      <Table dataSource={this.props.data.stages} pagination={{ pageSize: 7 }}>
        <Table.ColumnGroup>
          <Table.Column
            title={'方向'}
            dataIndex={'directions'}
            key={'directions'}
            render={item => item.map(i => (i === 0 ? '左' : '右')).join(', ')}
          />
          <Table.Column title={'总步数'} dataIndex={'steps'} key={'steps'} sorter={(a, b) => a.steps - b.steps} />
          <Table.Column
            width={'50'}
            title={'操作'}
            render={item => {
              return (
                <Popconfirm
                  title={'你确定将方向设置为' + item.directions.join(', ') + '？'}
                  onConfirm={() => this.selectStage(item)}
                  okText="确定"
                  cancelText="取消"
                >
                  <Button size={'mini'}>设置</Button>
                </Popconfirm>
              );
            }}
          />
        </Table.ColumnGroup>
      </Table>
    );
  };

  selectStage = item => {
    this.setState({
      step: 0,
      lastStep: 0,
      stageIndex: parseInt(item.directions.join(''), 2),
      modalVisible: false
    });
    this.pause();
  };

  start = () => {
    if (this.state.step >= this.props.data.stages[this.state.stageIndex].steps) return;
    this.updatePosition();
    this.intervalId = setInterval(this.updatePosition, updateInterval);
  };
  pause = () => {
    clearInterval(this.intervalId);
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
