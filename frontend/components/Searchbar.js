import { StyleSheet, ScrollView, View, Image } from "react-native";
import React, { Component, useEffect } from "react";
import SearchInput from "./SearchInput";
import StudySessionCard from "./StudySessionCard";
import assetsObject from "../assets/assets";
import { useState } from "react";
import { isNil } from "../utils/isNil";
import request_ressource from "../utils/fetchApi";

export default function Searchbar() {
 
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

  const sessions = data.map(function (event) {
    return {
      id: event.incoming.sessionId,
      tags: !isNil(event.incomingInfo?.tags)
        ? event.incomingInfo.tags[0]
        : null,
      sessionTitle: event.incoming.title,
      sessionLocation: isNil(event.incomingInfo?.location)
        ? null
        : event.incomingInfo.location,
      numberOfAttendees: event.incoming.numberOfAttendees,
      image: assetsObject.mcgillPhoto,
      description: event.incoming.description,
      startTime: event.incomingInfo?.startTime,
      endTime: event.incomingInfo?.endTime,
      creator: event.incomingInfo?.adminUsername,
    };
  });

  const [searchedSession, setSearchedSession] = React.useState("");
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
      if (session.sessionTitle != null) {
        if (
          session.sessionTitle.toLowerCase().search(search.toLowerCase()) !== -1
        ) {
          sessionsToShow.push(session);
        }
      }
      setFilteredSessions(sessionsToShow);
    });
  };
  useEffect(() => {
    filterSessions(searchedSession);
  }, [searchedSession]);

  return (
    <View>
      <SearchInput
        style={styles.container}
        icon={
          <Image
            style={styles.searchicon}
            source={require("../assets/searchicon.png")}
          ></Image>
        }
        placeholder="Search"
        state={searchedSession}
        setState={setSearchedSession}
      />
      <ScrollView>
        <View>
          {(showAll ? sessions : filteredSessions).map((session, index) => {
            return (
              <StudySessionCard
                key={index}
                tag={session.tag}
                sessionTitle={session.sessionTitle}
                sessionLocation={session.sessionLocation}
                numberOfAttendees={session.numberOfAttendees}
                image={session.image}
                description={session.description}
                startTime={session.startTime}
                endTime={session.endTime}
                creator={session.creator}
                sessionId={session.id}
              />
            );
          })}
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
  },
});
