import React from 'react';
import { themeColor } from './global/colors';
import { Button, Dropdown, InputNumber, message, Menu, Modal } from 'antd';
import SettingItem from './components/SettingItem';

const confirm = Modal.confirm;

const defaultConfig = () => ({
  config: { stickLength: 300, antDefaultSpeed: 5 },
  ants: [{ speed: -1, position: 10, direction: 1 }]
});

export default class SettingPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = defaultConfig();
  }

  render() {
    let { config, ants } = this.state;
    return (
      <div style={styles.container}>
        <div style={styles.settingsContainer}>
          <b>基本设置</b>
          <SettingItem label={'杆子长度'} value={config.stickLength} setValue={this.setValue('stickLength')} />
          <SettingItem label={'蚂蚁速度'} value={config.antDefaultSpeed} setValue={this.setValue('antDefaultSpeed')} />
          <div style={{ display: 'flex', alignItems: 'center', marginTop: 20, marginBottom: 20 }}>
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
          {ants.map(this.renderAntItem)}
        </div>

        <Button size={'large'} style={styles.buttonContainer} htmlType={'button'}>
          启动
        </Button>
        <Button size={'large'} style={styles.buttonContainer} htmlType={'button'} onClick={this.showDeleteConfirm}>
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

  renderAntItem = (ant, index) => {
    let menu = (
      <Menu>
        <Menu.Item key="1">左侧</Menu.Item>
        <Menu.Item key="2">右侧</Menu.Item>
      </Menu>
    );
    return (
      <div style={{ display: 'flex', alignItems: 'center', marginTop: 10 }}>
        <b style={{ flex: 1 }}>{index + 1 + ': '}</b>
        <span style={{ flex: 2 }}>方向</span>
        <Dropdown overlay={menu} trigger="click">
          <Button size={'small'} style={{ flex: 2, marginRight: 10 }}>
            {ant.direction !== 1 ? '左侧' : '右侧'}
          </Button>
        </Dropdown>
        <span style={{ flex: 2 }}>位置</span>
        <InputNumber
          size={'small'}
          style={{ flex: 2 }}
          value={ant.position}
          min={1}
          max={this.state.config.stickLength - 1}
        />
      </div>
    );
  };

  antChange = type => () => {
    let { ants } = this.state;
    if (type === 'plus') {
      ants.push({ speed: -1, position: 10, direction: 1 });
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
