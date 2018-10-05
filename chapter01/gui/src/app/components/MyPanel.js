import React from 'react';
import propTypes from 'prop-types';

export default class MyPanel extends React.Component {
  static propTypes = {
    panel: propTypes.string.isRequired,
    content: propTypes.string.isRequired
  };
  render() {
    return (
      <span style={{ marginBottom: 10 }}>
        <b>{this.props.panel}: </b>
        {this.props.content}
      </span>
    );
  }
}
