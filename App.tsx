import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  View
} from 'react-native';

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
  return (
    <SafeAreaView>
      <View style={styles.container}>
        <View>
          <Text style={styles.text}> Sync Blocking Method </Text>
          <Text style={styles.text}> {} </Text>
        </View>
        <View>
          <Text style={styles.text}> Callback Method </Text>
          <Text style={styles.text}> {} </Text>
        </View>
        <View>
          <Text style={styles.text}> Promise Method </Text>
          <Text style={styles.text}> {} </Text>
        </View>        
      </View>
    </SafeAreaView>
  );
}

export default App;
