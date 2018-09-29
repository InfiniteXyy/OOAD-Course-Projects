import React from 'react';
import ControlPanel from './components/ControlPanel';
import DisplayPanel from './components/DisplayPanel';
import propTypes from 'prop-types';

export default class MainPanel extends React.Component {
  static propTypes = {
    data: propTypes.object.isRequired
  };
  constructor(props) {
    super(props);
    this.controlMethods = {};
  }

  render() {
    return (
      <div style={styles.container}>
        <div style={{ flex: 1 }}>
          <p style={styles.title}>蚂蚁模拟器</p>
        </div>
        <div style={{ flex: 3 }}>
          <DisplayPanel
            data={this.props.data}
            bind={m => {
              for (let i in m) {
                this.controlMethods[i] = m[i];
              }
            }}
          />
        </div>
        <div style={{ flex: 2 }}>
          <ControlPanel methods={this.controlMethods} />
        </div>
      </div>
    );
  }
}

const styles = {
  container: {
    height: '100vh',
    display: 'flex',
    flex: 1,
    flexDirection: 'column'
  },
  title: {
    marginLeft: 20,
    marginTop: 20,
    fontWeight: '500',
    fontSize: 30,
    alignSelf: 'flex-end'
  }
};
