import { StyleSheet, ScrollView, View, Text, Button, Alert } from "react-native";

import AllSessionsComponent from "../../components/AllSessionsComponent";
import ActiveSessionsComponent from "../../components/ActiveSessionsComponent";
import UpcomingSessionsComponent from "../../components/UpcomingSessionsComponent";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";
import Searchbar from '../../components/Searchbar';

export default function Main({ navigation }) {

  return (
    <View>
      <Searchbar />
      <AllSessionsComponent navigation={navigation} />
      <ActiveSessionsComponent navigation={navigation} />
      <UpcomingSessionsComponent navigation={navigation}/>
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 10,
  },
  title: {
    fontSize: 24,
    marginHorizontal: 15,
    marginTop: 5,
  }
});