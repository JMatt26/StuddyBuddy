import { View } from "react-native";
import AllSessionsComponent from "../../components/AllSessionsComponent";
import ActiveSessionsComponent from "../../components/ActiveSessionsComponent";
import UpcomingSessionsComponent from "../../components/UpcomingSessionsComponent";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";
import Searchbar from '../../components/Searchbar';

export default function Main() {

  return (
    <View>
      <Searchbar />
      <AllSessionsComponent />
      <ActiveSessionsComponent />
      <UpcomingSessionsComponent />
    </View>
  );
}

