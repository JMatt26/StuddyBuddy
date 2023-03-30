import { View } from "react-native";
 import ActiveSessions from "../../components/ActiveSessions";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";

export default function Main() {
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
      <ActiveSessions />
    </View>
  );

}

