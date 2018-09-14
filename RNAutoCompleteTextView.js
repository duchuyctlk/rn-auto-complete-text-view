import PropTypes from 'prop-types';
import React from 'react'
import { requireNativeComponent, View } from 'react-native';

class RNAutoCompleteTextView extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <NativeAutoCompleteTextView { ...this.props } />;
  }
}

RNAutoCompleteTextView.propTypes = {
  dataSource: PropTypes.array,
  text: PropTypes.object,
  completionHint: PropTypes.string,
  completionThreshold: PropTypes.number,
  showDropDown: PropTypes.bool,
  ...View.propTypes
};

var NativeAutoCompleteTextView = requireNativeComponent('NativeAutoCompleteTextView');

export { RNAutoCompleteTextView }
