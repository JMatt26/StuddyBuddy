import request_ressource from "frontend/utils/fetchApi.js";
import { useState, useEffect } from "react";
import { isNil } from "frontend/utils/isNil.js";
import React, { Component } from "react";
import ActiveSession from "./ActiveSession";
import { StyleSheet, ScrollView, View, Text, Button } from "react-native";

export default function ActiveSessions() {
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
  

  return (
    <View>
      <View style={styles.container}>
          <Text style={styles.title}>All Sessions</Text>
          <Button color="#0ead69" title="See All" />
      </View>
      <ScrollView horizontal={true}>
      {data.map((event, index) => {
        return(
          <View key={index}>
          <ActiveSession 
          sessionName={event.incoming.title}
          location={isNil(event.incomingInfo?.location) ? null : event.incomingInfo.location} 
          attendanceNbr={event.incoming.numberOfAttendees}/>
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

