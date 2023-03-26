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
    url = `http://localhost:8080/session/getAllActiveSessions`;
    
    let response = null;
    try {
      response = await request_ressource(url, "GET");
      setStatusCode(response.status);
      response = await response.json();
      setData(response);
    } catch (e) {
      console.log(e);
    }
  }
  
  useEffect(() => {
    getActiveSessionsFromServer();
  }, []);

  let renderSessions = "";
  renderSessions = data.map((event) => (
    <View>
      <StudySessionCard
        tag={event.title}
        sessionTitle={"Studying Session"}
        sessionLocation={"Trottier"}
        numberOfAttendees={12}
        image={assetsObject.mcgillPhoto}
      />
    </View>
  ));


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
