import request_ressource from "frontend/utils/fetchApi.js";
import { useState, useEffect } from "react";
import { isNil } from "frontend/utils/isNil.js";
import React, { Component } from "react";
import HomeCard from "./HomeCard";
import { StyleSheet, ScrollView, View, Text, Button, Alert } from "react-native";

export default function AllSessionsComponent() {
  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");
  
  async function getAllSessionsFromServer() {
    let url = "";
    url = `http://localhost:8080/session/getAllSessions`;
    
    let response = null;
    try {
      response = await request_ressource(url, "GET");
      response = await response.body.json();
      console.log(response);
      setData(response);
    } catch (e) {
      console.log(e);
    }
  }
  
  useEffect(() => {
    getAllSessionsFromServer();
  }, []);
  
  const routeChange = () => {
    Alert.alert("Session");
  };

  return (
    <View>
      <View style={styles.container}>
          <Text style={styles.title}>All Sessions</Text>
          <Button onPress={routeChange} color="#0ead69" title="See All" />
      </View>
      <ScrollView horizontal={true}>
      {data.map((event, index) => {
        return(
          <View key={index}>
          <HomeCard 
          sessionName={event.incoming.title}
          location={isNil(event.incomingInfo?.location) ? 'Online' : event.incomingInfo.location} 
          attendanceNbr={event.incoming.numberOfAttendees}
          sessionId={event.incoming.sessionId}/>
          </View>
        );
      })}
    </ScrollView>
    </View>
    
  );

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

