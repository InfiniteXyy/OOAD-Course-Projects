import React from 'react';
import { themeColor } from './global/colors';
import { Button, InputNumber, message, Menu, Modal } from 'antd';
import SettingItem from './components/SettingItem';
import propTypes from 'prop-types';

const confirm = Modal.confirm;

const defaultConfig = () => ({
  config: { stickLength: 300, antDefaultSpeed: 5 },
  ants: [10]
});

export default class SettingPanel extends React.Component {
  static propTypes = {
    gameControl: propTypes.shape({
      start: propTypes.func,
      stop: propTypes.func
    }).isRequired
  };
  constructor(props) {
    super(props);
    this.state = { ...defaultConfig() };
    this.state.isStart = false;
  }

  render() {
    let { config, ants } = this.state;
    return (
      <div style={styles.container}>
        <div style={styles.settingsContainer}>
          <b>基本设置</b>
          <SettingItem label={'杆子长度'} value={config.stickLength} setValue={this.setValue('stickLength')} />
          <SettingItem label={'蚂蚁速度'} value={config.antDefaultSpeed} setValue={this.setValue('antDefaultSpeed')} />
          <div
            style={{
              display: 'flex',
              alignItems: 'center',
              marginTop: 20,
              marginBottom: 20
            }}
          >
            <b style={{ marginRight: 20 }}>蚂蚁设置</b>
            <Button.Group>
              <Button
                htmlType={'button'}
                icon={'minus'}
                onClick={this.antChange('minus')}
                disabled={this.state.ants.length === 1}
              />
              <Button
                htmlType={'button'}
                icon={'plus'}
                onClick={this.antChange('plus')}
                disabled={this.state.ants.length === 7}
              />
            </Button.Group>
          </div>
          <a onClick={this.useDemo}>使用样例数据</a>

          {ants.map(this.renderAntItem)}
        </div>

        <Button
          size={'large'}
          style={styles.buttonContainer}
          htmlType={'button'}
          type={this.state.isStart ? 'danger' : 'primary'}
          onClick={this.handleMainButton}
        >
          {this.state.isStart ? '结束' : '启动'}
        </Button>
        <Button
          size={'large'}
          style={styles.buttonContainer}
          htmlType={'button'}
          onClick={this.showDeleteConfirm}
          disabled={this.state.isStart}
        >
          重置
        </Button>
      </div>
    );
  }

  setValue = type => value => {
    this.setState(({ config }) => {
      let newConfig = config;
      newConfig[type] = value;
      return { config: newConfig };
    });
  };

  setAntPosition = i => v => {
    this.setState({
      ants: this.state.ants.map((value, index) => {
        if (index === i) return v;
        return value;
      })
    });
  };

  renderAntItem = (ant, index) => {
    return (
      <div key={index.toString()} style={{ display: 'flex', alignItems: 'center', marginTop: 10 }}>
        <b>{`Ant ${index + 1}: `}</b>
        <span style={{ marginLeft: 10, marginRight: 10 }}> 位置</span>
        <InputNumber
          size={'small'}
          value={ant}
          min={1}
          max={this.state.config.stickLength - 1}
          onChange={this.setAntPosition(index)}
        />
      </div>
    );
  };

  handleMainButton = () => {
    let { start, stop } = this.props.gameControl;
    if (!this.state.isStart) {
      start({
        positions: this.state.ants.join(','),
        length: this.state.config.stickLength,
        velocity: this.state.config.antDefaultSpeed
      });
    } else {
      stop();
    }

    this.setState(({ isStart }) => {
      return { isStart: !isStart };
    });
  };

  useDemo = () => {
    this.setState({
      config: { stickLength: 300, antDefaultSpeed: 5 },
      ants: [30, 80, 110, 160, 250]
    });
  };

  antChange = type => () => {
    let { ants } = this.state;
    if (type === 'plus') {
      // add new ant
      ants.push(1);
    } else {
      this.state.ants.pop();
    }
    this.forceUpdate();
  };

  showDeleteConfirm = () => {
    confirm({
      title: '你确定要重置所有设定吗？',
      content: '这将不可还原',
      okText: '重置',
      okType: 'danger',
      cancelText: '取消',
      onOk: () => {
        this.setState(defaultConfig());
        message.success('重置设置完成');
      },
      onCancel() {
        console.log('Cancel');
      }
    });
  };
}

const styles = {
  container: {
    padding: 20,
    paddingBottom: 50,
    paddingTop: 20,
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'flex-end',
    width: 250,
    backgroundColor: themeColor.backgroundColor
  },
  buttonContainer: {
    marginTop: 16
  },
  settingsContainer: {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    paddingTop: 10
  }
};
