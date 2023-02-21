import { StyleSheet, View, Text, Pressable, Image } from "react-native";

const assets = require("../assets/assets.js");

//to pass an image, add the image to the object in assets.js in the assets folder and pass the value related to the image as the prop
export default function StudySessionCard({
  courseTitle,
  sessionTitle,
  sessionLocation,
  numberOfAttendees,
  image,
}) {
  return (
    <View>
      <View style={styles.container}>
        <View style={styles.image}>
          <Image source={image} style={styles.image} />
        </View>
        <View style={styles.sessionInfoContainer}>
          <View style={styles.courseTitleContainer}>
            <Text>{courseTitle}</Text>
          </View>
          <Text style={styles.sessionTitle}>{sessionTitle}</Text>
          <Text style={styles.sessionLocation}>{sessionLocation}</Text>
        </View>
        <View style={styles.sessionButtonContainer}>
          <Pressable>
            <Image source={require("../assets/plusbutton.png")}></Image>
          </Pressable>
          <Text style={{ fontSize: 10 }}>{numberOfAttendees + " going"}</Text>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    display: "flex",
    flexDirection: "row",
    margin: 10,
    padding: 10,
    height: 115,
    borderRadius: 10,
    backgroundColor: "#FFFFFF",
    alignItems: "center",
  },
  image: {
    width: 120,
    height: 87,
    borderRadius: 10,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.8,
    shadowRadius: 1,
  },
  sessionInfoContainer: {
    marginLeft: 10,
  },
  courseTitleContainer: {
    margin: 5,
    padding: 5,
    backgroundColor: "#03B0A9",
    borderRadius: 10,
    maxWidth: "50%",
    alignItems: "center",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.8,
    shadowRadius: 1,
  },
  sessionTitle: {
    margin: 5,
    fontSize: 16,
    fontWeight: "bold",
  },
  sessionLocation: {
    margin: 5,
  },
  sessionButtonContainer: {
    display: "flex",
    flexDirection: "column",
    height: "90%",
    width: "15%",
    justifyContent: "space-between",
  },
});
