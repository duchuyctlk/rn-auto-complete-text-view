/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import { RNAutoCompleteTextView } from './RNAutoCompleteTextView';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {selectedItem: ""};
    this.onItemClick = this.onItemClick.bind(this);
    this.onDismiss = this.onDismiss.bind(this);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>{this.state.selectedItem !== '' ? this.state.selectedItem : 'Welcome to React Native!'}</Text>
        <Text style={styles.instructions}>To get started, edit App.js</Text>
        <Text style={styles.instructions}>{instructions}</Text>
        <RNAutoCompleteTextView
          style={{ height: 50, margin: 16 }}
          dataSource={['preBuild',
                       'preDebugBuild',
                       'compileDebugAidl',
                       'compileDebugRenderscript',
                       'checkDebugManifest',
                       'generateDebugBuildConfig',
                       'prepareLintJar',
                       'mainApkListPersistenceDebug',
                       'bundleDebugJsAndAssets',
                       'generateDebugResValues',
                       'generateDebugResources',
                       'mergeDebugResources',
                       'createDebugCompatibleScreenManifests',
                       'processDebugManifest',
                       'splitsDiscoveryTaskDebug',
                       'processDebugResources',
                       'generateDebugSources']}
          completionThreshold={ 1 }
          onItemClick={ this.onItemClick }
          onDismiss={ this.onDismiss }
         >
        </RNAutoCompleteTextView>
      </View>
    );
  }

  onItemClick(clickedItem) {
    // this.setState({
    //   selectedItem: clickedItem
    // });
  }

  onDismiss(event: Event) {
    this.setState({
      selectedItem: 'onDismiss'
    });
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    alignItems: 'stretch'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
