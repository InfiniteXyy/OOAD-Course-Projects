import React from 'react';
import { InputNumber } from 'antd';
import propTypes from 'prop-types';

export default class SettingItem extends React.Component {
  static propTypes = {
    label: propTypes.string.isRequired,
    value: propTypes.any.isRequired,
    setValue: propTypes.func.isRequired
  };
  render() {
    return (
      <div style={styles.container}>
        <span style={styles.label}>{this.props.label}</span>
        <InputNumber value={this.props.value} onChange={this.props.setValue} min={1} />
      </div>
    );
  }
}

const styles = {
  container: {
    display: 'flex',
    marginTop: 16,
    alignItems: 'center'
  },
  label: {
    fontSize: 14,
    fontWeight: '500',
    marginRight: 20
  }
};
