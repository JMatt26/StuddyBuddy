import {StyleSheet, ScrollView, View, Image} from "react-native";
import React, { Component, useEffect } from "react";
import SearchInput from "./SearchInput";
import StudySessionCard from "./StudySessionCard";
import assetsObject from "../assets/assets";
import {useState} from "react";

export default function Searchbar() {
    // const sessions = [
    //   {id: 1, tag: 'FACC-300', sessionTitle: 'Study Session', sessionLocation: "Sherbrooke 680", numberOfAttendees: '36', image:assetsObject.mcgillPhoto},
    //   {id: 2, tag: "ECSE-324", sessionTitle: "Studying Session", sessionLocation: "Trottier", numberOfAttendees: "12", image: assetsObject.mcgillPhoto},
    //   {id:3, tag: "COMP-251", sessionTitle: "COMP 251 Final Review", sessionLocation: "Trottier", numberOfAttendees: "2", image: assetsObject.mcgillPhoto},
    //   {id: 4, tag: "COMP-206", sessionTitle: "COMP 206 Midterm Review", sessionLocation: "Trottier", numberOfAttendees: 12, image: assetsObject.mcgillPhoto},
    // ];

    const [data, setData] = useState([]);
    const [status, setStatus] = useState("");
    const sessions = [];

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

    let setSessionTitles = data.map((event, index) => {
      sessions.push(event.incoming.title);
      console.log(event.title);
    }
    );

    const [searchedSession, setSearchedSession] = React.useState('');
    const [filteredSessions, setFilteredSessions] = React.useState([]);
    const [showAll, setShowAll] = React.useState();

    const filterSessions = (search) => {
      if (search == null || search == undefined || search == "") {
        setShowAll(true);
        return;
      }
      setShowAll(false);
      let sessionsToShow = [];
      sessions.forEach((session) => {
        if (session.sessionTitle.toLowerCase().search(search.toLowerCase()) !== -1) {
          sessionsToShow.push(session);
        }
        setFilteredSessions(sessionsToShow);
      });

    }
    useEffect(() => {
      filterSessions(searchedSession);
    }, [searchedSession])

    return (
      <View >
      <SearchInput
        style={styles.container}
        icon= {<Image style={styles.searchicon} source={require("../assets/searchicon.png")}></Image>}
        placeholder="Search"
        state={searchedSession}
        setState={setSearchedSession}
      />
      <ScrollView>
        <View>
          {(showAll ? sessions : filteredSessions).map(
            (session) => {
              return (
                <StudySessionCard key={session.id} tag={session.tag} sessionTitle={session.sessionTitle} sessionLocation={session.sessionLocation} numberOfAttendees={session.numberOfAttendees} image={session.image}/>
              );
            }
          )}
        </View>
      </ScrollView>
      </View>
    );
}

const styles = StyleSheet.create({
  searchicon: {
    width: "4%",
    height: "4%",
    marginLeft: "4%",
    padding: 9,
  }
});