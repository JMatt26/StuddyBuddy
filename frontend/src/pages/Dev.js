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
  return (
    <ScrollView>
      <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center", flex: 1 }}>
        ! FOR DEVELOPMENT ONLY !
      </Text>
    </ScrollView>
  );
}
