import { View, Text } from "react-native";
import SearchSessionCard from "../components/SearchSessionCard";
import StudySessionCard from "../components/StudySessionCard";

const assets = require("../assets/assets.js");

export default function Dev() {
  return (
    <View>
      <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
        ! FOR DEVELOPMENT ONLY !
      </Text>
      <StudySessionCard
        courseTitle={"ECSE 324"}
        sessionTitle={"Exam Study Session"}
        sessionLocation={"ENGTR-200"}
        numberOfAttendees={19}
        image={assets["mcgillPhoto"]}
      />
    </View>
  );
}
