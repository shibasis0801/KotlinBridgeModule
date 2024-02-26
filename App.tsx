import React, { useEffect, useState } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  View
} from 'react-native';

import { NativeModules } from 'react-native';
const { NormalBridgeModule, DarwinBridgeModule } = NativeModules;
console.log("NormalBridgeModule", NormalBridgeModule);
console.log("DarwinBridgeModule", DarwinBridgeModule);

const styles = StyleSheet.create({
  container: {
    height: '100%',
    width: '100%',
    justifyContent: 'space-around',
    padding: 16,
  },
  text: {
    fontSize: 24
  }
});

function App(): React.JSX.Element {
  const [ callbackResult, setCallbackResult ] = useState('');
  useEffect(() => {
    DarwinBridgeModule?.normalAsyncFunction(setCallbackResult)
  }, [])

  const [ promiseResult, setPromiseResult ] = useState('');
  useEffect(() => {
    DarwinBridgeModule?.promiseFunction().then(setPromiseResult)
  }, [])


  return (
    <SafeAreaView>
      <View style={styles.container}>
        <View>
          <Text style={styles.text}> Sync Blocking Method </Text>
          <Text style={styles.text}> {DarwinBridgeModule?.syncBlockingFunction()} </Text>
        </View>
        <View>
          <Text style={styles.text}> Callback Method </Text>
          <Text style={styles.text}> {callbackResult} </Text>
        </View>
        <View>
          <Text style={styles.text}> Promise Method </Text>
          <Text style={styles.text}> {promiseResult} </Text>
        </View>        
      </View>
    </SafeAreaView>
  );
}

export default App;
