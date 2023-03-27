import { View, Text } from "react-native";
import ActiveSessions from "../../components/ActiveSessions";
import Categories from "../../components/Categories";
import CategoryButton from "../../components/CategoryButton";
import { Pressable } from "react-native";
import { AddUser } from "../../utils/AddUser";
import StudySessionCard from "../../components/StudySessionCard";
import assetsObject from "../../assets/assets";
import ActiveSessionsPage from "./ActiveSessionsPage";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";

export default function Dev() {
  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");
  
  async function getActiveSessionsFromServer() {
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
    getActiveSessionsFromServer();
  }, []);

  let renderSessions = "";
  renderSessions = data.map(event => {
  return (<View>
    <StudySessionCard
      tag={event.incomingInfo?.tags.length > 0 ? event.incomingInfo.tag[0] : null}
      sessionTitle={event.incoming.title}
      sessionLocation={"McGill University"}
      numberOfAttendees={event.incoming.numberOfAttendees}
      image={assetsObject.mcgillPhoto}
      description={event.incoming.description}
      sessionId={event.incoming.sessionId}
    />
  </View> )
  }
  );


  return (
    <View>
      <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
        ! FOR DEVELOPMENT ONLY !
      </Text>
      {/* <StudySessionCard
        tag={"ECSE-324"}
        sessionTitle={"Studying Session"}
        sessionLocation={"Trottier"}
        numberOfAttendees={12}
        image={assetsObject.mcgillPhoto}
      /> */}
      {renderSessions}
    </View>
  );
}
