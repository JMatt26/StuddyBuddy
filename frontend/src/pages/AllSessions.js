import { View, Text, ScrollView } from "react-native";
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

export default function AllSessions() {
  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");
  
  async function getAllSessionsFromServer() {
    let url = "";
    url = `http://localhost:8080/session/getAllSessions`;
    
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
    getAllSessionsFromServer();
  }, []);

  let renderSessions = "";
  renderSessions = data.map((event, index) => {
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
      {renderSessions}
    </ScrollView>
  );
}
