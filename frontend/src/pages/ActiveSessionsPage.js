import { useState } from "react";
import request_ressource from "../../utils/fetchApi";
import renderSessions from "../../utils/renderSessions";

export default function ActiveSessions() {
  async function getActiveSessionsFromServer() {
    const [data, setData] = useState([]);
    const [status, setStatus] = useState("");
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

  return (
    <View>
      <renderSessions data={data} />
    </View>
  );
}
