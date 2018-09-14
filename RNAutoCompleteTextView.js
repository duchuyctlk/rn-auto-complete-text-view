import PropTypes from 'prop-types';
import React from 'react'
import { requireNativeComponent, View, DeviceEventEmitter } from 'react-native';

class RNAutoCompleteTextView extends React.Component {
  constructor(props) {
    super(props);
    
    this.onItemClick = this.onItemClick.bind(this);
    this.onDismiss = this.onDismiss.bind(this);
  }

  render() {
    return <NativeAutoCompleteTextView { ...this.props } />;
  }

  componentWillMount() {
    DeviceEventEmitter.addListener('onItemClick', this.onItemClick);
    DeviceEventEmitter.addListener('onDismiss', this.onDismiss);
  }

  componentWillUnmount() {
    DeviceEventEmitter.removeListener('onItemClick', this.onItemClick);
    DeviceEventEmitter.removeListener('onDismiss', this.onDismiss);
  }

  onItemClick(event: Event) {
    if (!this.props.onItemClick) {
      return;
    }
    this.props.onItemClick(event.clickedItem);
  }

  onDismiss(event: Event) {
    if (!this.props.onDismiss) {
      return;
    }
    this.props.onDismiss(event);
  }
}

RNAutoCompleteTextView.propTypes = {
  dataSource: PropTypes.array,
  text: PropTypes.object,
  completionHint: PropTypes.string,
  completionThreshold: PropTypes.number,
  showDropDown: PropTypes.bool,

  onItemClick: PropTypes.func,
  onDismiss: PropTypes.func,

  ...View.propTypes
};

var NativeAutoCompleteTextView = requireNativeComponent('NativeAutoCompleteTextView');

export { RNAutoCompleteTextView }
