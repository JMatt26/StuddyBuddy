import React, { Component } from "react";
import ActiveSession from "./ActiveSession";
import { StyleSheet, View, Text, Button } from "react-native";

class ActiveSessions extends Component {

  render() {
    return (
    <View>
        <View style={styles.container}>
          <Text style={styles.title}>Active Sessions</Text>
          <Button color="#0ead69" title="See All" />
        </View>
        
        <View style={styles.activeSessions}>
            <ActiveSession />
            <ActiveSession />
            <ActiveSession />
        </View>
    </View>
    );
  }
}

const styles = StyleSheet.create({
    container: {
      display: "flex",
      flexDirection: "row",
      justifyContent: "space-between",
      marginTop: 10,
    },
    title: {
      fontSize: 24,
      marginHorizontal: 15,
      marginTop: 5,
    },
    activeSessions: {
        display: "flex",
        flexDirection: "row",
        flexWrap: "wrap",
        alignSelf: "flex-start",
        marginHorizontal: 15,
        marginVertical: 5,
        justifyContent: "space-between",
    }
  });

export default ActiveSessions;
