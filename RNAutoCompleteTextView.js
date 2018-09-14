import PropTypes from 'prop-types';
import React from 'react'
import { requireNativeComponent, View, DeviceEventEmitter } from 'react-native';

class RNAutoCompleteTextView extends React.Component {
  constructor(props) {
    super(props);
    
    this.onItemClick = this.onItemClick.bind(this);
  }

  render() {
    return <NativeAutoCompleteTextView { ...this.props } />;
  }

  componentWillMount() {
    DeviceEventEmitter.addListener('onItemClick', this.onItemClick);
  }

  componentWillUnmount() {
    DeviceEventEmitter.removeListener('onItemClick', this.onItemClick);
  }

  onItemClick(event: Event) {
    if (!this.props.onItemClick) {
      return;
    }
    this.props.onItemClick(event.clickedItem);
  }
}

RNAutoCompleteTextView.propTypes = {
  dataSource: PropTypes.array,
  text: PropTypes.object,
  completionHint: PropTypes.string,
  completionThreshold: PropTypes.number,
  showDropDown: PropTypes.bool,

  onItemClick: PropTypes.func,

  ...View.propTypes
};

var NativeAutoCompleteTextView = requireNativeComponent('NativeAutoCompleteTextView');

export { RNAutoCompleteTextView }
