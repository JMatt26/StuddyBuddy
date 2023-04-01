import Searchbar from "../../components/Searchbar"
import { View, Text, ScrollView } from "react-native";
import ActiveSessions from "../../components/ActiveSessions";
import Categories from "../../components/Categories";
import CategoryButton from "../../components/CategoryButton";
import { Pressable } from "react-native";
import { AddUser } from "../../utils/AddUser";
import StudySessionCard from "../../components/StudySessionCard";
import assetsObject from "../../assets/assets";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";

export default function Dev() {
  // const [data, setData] = useState([]);
  // const [status, setStatus] = useState("");
  
  // async function getActiveSessionsFromServer() {
  //   let url = "";
  //   url = `http://localhost:8080/session/getAllSessions`;
    
  //   let response = null;
  //   try {
  //     response = await request_ressource(url, "GET");
  //     setStatus(response.status);
  //     response = await response.body.json();
  //     console.log(response);
  //     setData(response);
  //   } catch (e) {
  //     console.log(e);
  //   }
  // }
  
  // useEffect(() => {
  //   getActiveSessionsFromServer();
  // }, []);

  const sessions = [
    {tag: 'FACC-300', sessionTitle: 'Study Session', sessionLocation: "Sherbrooke 680", numberOfAttendees: '36', image:assetsObject.mcgillPhoto},
    {tag: "ECSE-324", sessionTitle: "Studying Session", sessionLocation: "Trottier", numberOfAttendees: "12", image: assetsObject.mcgillPhoto},
    {tag: "COMP-251", sessionTitle: "COMP 251 Final Review", sessionLocation: "Trottier", numberOfAttendees: "2", image: assetsObject.mcgillPhoto},
    {tag: "COMP-206", sessionTitle: "COMP 206 Midterm Review", sessionLocation: "Trottier", numberOfAttendees: 12, image: assetsObject.mcgillPhoto},
  ];

    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <Searchbar sessions={sessions}/>
    </View>)
}
