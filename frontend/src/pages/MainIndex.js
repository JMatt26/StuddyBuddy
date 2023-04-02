import { StyleSheet, ScrollView, View, Text, Button, Alert } from "react-native";
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { NavigationContainer } from "@react-navigation/native";


import AllSessionsComponent from "../../components/AllSessionsComponent";
import ActiveSessionsComponent from "../../components/ActiveSessionsComponent";
import UpcomingSessionsComponent from "../../components/UpcomingSessionsComponent";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";
import Searchbar from '../../components/Searchbar';
import Main from './Main';
import AllSessions from "./AllSessions";
import UpcomingSessions from "./UpcomingSessions";
import ActiveSessions from "./ActiveSessions";


const Stack = createNativeStackNavigator();

export default function MainIndex({ navigation }) {

  

  return (
    <NavigationContainer independent={true}>
      <Stack.Navigator screenOptions={{
        headerShown: false
        }}>
        <Stack.Screen name="Main" component={Main} />
        <Stack.Screen name="AllSessions" component={AllSessions} />
        <Stack.Screen name="ActiveSessions" component={ActiveSessions} />
        <Stack.Screen name="UpcomingSessions" component={UpcomingSessions} />
      </Stack.Navigator>
    </NavigationContainer>
    
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