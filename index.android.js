/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  TextInput,
  View,
  Switch
} from 'react-native';
class EasyParking extends Component {
  constructor(props){
    super(props);
    this.state = {trueSwitchIsOn:false};
    this._handleSwitchChange = this._handleSwitchChange.bind(this);
  }
  render() {
    return (
      <View style={styles.container}>   
        <Text style={styles.welcome}>
          Welcome to Easy Parking
        </Text>
        <Text>
          Auto Navigate
        </Text>
        <Switch onValueChange={value=>this._handleSwitchChange(value)} style={{marginBottom: 10}} value={this.state.trueSwitchIsOn}/>      
      </View>
    );
  }
  
  _handleSwitchChange(value){
    this.setState({trueSwitchIsOn:value});
  }
  shouldComponentUpdate(nextProps, nextStates){
    if (this.state.trueSwitchIsOn){
      React.NativeModules.LocationTrackingBridge.stopService();
    }else{
      React.NativeModules.LocationTrackingBridge.startService();
    }
    return true;
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
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

AppRegistry.registerComponent('EasyParking', () => EasyParking);
