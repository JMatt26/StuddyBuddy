import { StyleSheet, View, Text, ScrollView } from "react-native";
import AllSessionsComponent from "../../components/AllSessionsComponent";
import Categories from "../../components/Categories";
import CategoryButton from "../../components/CategoryButton";
import { Pressable } from "react-native";
import { AddUser } from "../../utils/AddUser";
import StudySessionCard from "../../components/StudySessionCard";
import assetsObject from "../../assets/assets";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";
import { useIsFocused } from "@react-navigation/native";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function AllSessions() {
  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");
  const [createdSessions, setCreatedSessions] = useState([]);
  const [joinedSessions, setJoinedSessions] = useState([]);
  const isFocused = useIsFocused();
   
  async function getAllSessionsFromServer() {
    let url = "";
    url = `http://localhost:8080/session/getAllSessionsByUsername`;
    
    let response = null;
    try {
      response = await request_ressource(url, "GET");
      setStatus(response.status);
      response = await response.body.json();
      console.log(response);
      setData(response);
    } catch (e) {
      console.log(e);
    }
  }
  
  useEffect(() => {
    if (isFocused) getAllSessionsFromServer();
  }, [isFocused]);

  async function categorizeSessions() {
    let adminSessions = [];
    let participantSessions = [];
    let username = await AsyncStorage.getItem("username");
    data.forEach((session) => {
        if(session.incomingInfo?.adminUsername == username) {
           adminSessions.push(session); 
        } else {
            participantSessions.push(session); 
        }
        
    });
    setCreatedSessions(adminSessions);
    setJoinedSessions(participantSessions);
  } 
  
  useEffect(() => {
    categorizeSessions(); 
  }, [data])


  let renderCreatedSessions = "";
  renderCreatedSessions = createdSessions.map((event, index) => {
  return (
  <View key={index} style={{flex: 1}}>
    <StudySessionCard
      tag={!isNil(event.incomingInfo?.tags)? event.incomingInfo.tags[0] : null}
      sessionTitle={event.incoming.title}
      sessionLocation={isNil(event.incomingInfo?.location) ? null : event.incomingInfo.location}
      numberOfAttendees={event.incoming.numberOfAttendees}
      image={assetsObject.mcgillPhoto}
      description={event.incoming.description}
      startTime={event.incomingInfo?.startTime}
      endTime={event.incomingInfo?.endTime}
      creator={event.incomingInfo?.adminUsername}
    />
  </View> )
  }
  );

  let renderJoinedSessions = "";
  renderJoinedSessions = joinedSessions.map((event, index) => {
  return (
  <View key={index} style={{flex: 1}}>
    <StudySessionCard
      tag={!isNil(event.incomingInfo?.tags)? event.incomingInfo.tags[0] : null}
      sessionTitle={event.incoming.title}
      sessionLocation={isNil(event.incomingInfo?.location) ? null : event.incomingInfo.location}
      numberOfAttendees={event.incoming.numberOfAttendees}
      image={assetsObject.mcgillPhoto}
      description={event.incoming.description}
      startTime={event.incomingInfo?.startTime}
      endTime={event.incomingInfo?.endTime}
      creator={event.incomingInfo?.adminUsername}
      sessionId={event.incoming.sessionId}

    />
  </View> )
  }
  );

  return (
    <ScrollView>
      <Text style = {styles.text}>Admin Sessions</Text>
      {renderCreatedSessions}
      {renderCreatedSessions.length == 0 ? <Text style = {styles.emptyText}>No Created Sessions yet!</Text> : null} 
      <Text style = {styles.text}>Joined Sessions</Text>
      {renderJoinedSessions}
      {renderJoinedSessions.length == 0 ? <Text style = {styles.emptyText}>No Joined Sessions yet!</Text> : null} 
    </ScrollView>
  );
}

const styles = StyleSheet.create({
    text: {
        fontSize: 24,
        marginHorizontal: 15,
        marginTop: "3%",
    },
    emptyText: {
        fontSize: 15,
        marginHorizontal: 15,
        marginTop: "3%",
        marginBottom: "5%",
    }
});