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
  Linking,
} from 'react-native';
const GOOGLE_MAP_BASE_URL = "https://maps.google.com.tw/maps";
class EasyParking extends Component {
  constructor(props){
    super(props);
    this.state = {dest:null};
    this._handleDestinationChange = this._handleDestinationChange.bind(this);
    React.NativeModules.LocationTrackingBridge.startService();
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to Easy Parking
        </Text>
        <Text style={styles.instructions}>
          Please Enter the destination with format (latitude, longitude)
        </Text>
        <TextInput onSubmitEditing={event=>this._handleDestinationChange(event)} style={styles.instructions}>
        </TextInput>
      </View>
    );
  }
  
  _handleDestinationChange(event){
    this.setState({dest:event.nativeEvent.text});
  }
  shouldComponentUpdate(nextProps, nextStates){
    //Test if stopService would work
    React.NativeModules.LocationTrackingBridge.stopService();
    if (nextStates&&nextStates.dest){
      let pos = nextStates.dest.split(/,/);
      try{
        if (pos[0].match(/^\d+[\.]?\d+$/) && pos[1].match(/^\d+[\.]?\d+$/)){
          this.invokeGoogleDirection(pos[0], pos[1]);
        }else{
          throw "Error format!";
        }
      }catch(e){
        return false
      }
    }
    
    return true;
  }
  invokeGoogleDirection(lat, long){
    let url = this.buildGoogleMapURL(lat,long);
    Linking.canOpenURL(url)
    .then(supported=>{
        if (!supported){
          console.log("Can not handle url "+ url);
        }else{
          return Linking.openURL(url);
        }
    }).catch(err=>console.log('An error occured', err));
  }
  
  buildGoogleMapURL(lat, long){
    return GOOGLE_MAP_BASE_URL+'?daddr='+lat+','+long;
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
