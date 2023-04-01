import {StyleSheet, ScrollView, View, Image} from "react-native";
import React, { Component, useEffect } from "react";
import SearchInput from "./SearchInput";
import StudySessionCard from "./StudySessionCard";
import assetsObject from "../assets/assets";

export default function Searchbar({sessions = [{}]}) {

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
            (session, index) => {
              return (
                <StudySessionCard key={index} tag={session.tag} sessionTitle={session.sessionTitle} sessionLocation={session.sessionLocation} numberOfAttendees={session.numberOfAttendees} image={session.image}/>
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