import { View } from "react-native";
 import AllSessionsComponent from "../../components/AllSessionsComponent";
import request_ressource from "../../utils/fetchApi";
import { useState, useEffect } from "react";
import { isNil } from "../../utils/isNil";

export default function Main() {

  return (
    <View>
      <AllSessionsComponent />
    </View>
  );
}

