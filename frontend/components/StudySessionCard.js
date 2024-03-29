import { StyleSheet, View, Text, Image, Modal, TouchableOpacity } from "react-native";
import { isNil } from "../utils/isNil.js";
import { Pressable, Divider } from "@react-native-material/core";
import React, {useState} from 'react';
import {AddUser} from "../utils/AddUser";

const assets = require("../assets/assets.js");


//to pass an image, add the image to the object in assets.js in the assets folder and pass the value related to the image as the prop
export default function StudySessionCard({
  tag,
  sessionTitle,
  sessionLocation,
  numberOfAttendees,
  image,
  description,
  startTime,
  endTime,
  creator,
  sessionId,
}) {
  const [modalVisible, setModalVisible] = useState(false);
  let cardMeetingPlace = isNil(sessionLocation) ? "Online" : "In Person";
  let modalMeetingPlace = isNil(sessionLocation) ? "Online" : sessionLocation;
  return (
    <View>

      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => {
          Alert.alert('Modal has been closed.');
          setModalVisible(!modalVisible);
        }}>
        <View style={styles.centeredView}>
          <View style={styles.modalView}>
            <Text style={styles.modalTitle}> {sessionTitle} </Text>
            <Text style = {{marginBottom: 15}} > 
              <Text style={styles.modalInfoHeader}> Hosted By: </Text>
              <Text style={styles.modalText}> {creator} </Text>
            </Text> 
            <Text style = {{marginBottom: 15}}> 
              <Text style={styles.modalInfoHeader}> Location: </Text> 
              <Text style={styles.modalText}> {modalMeetingPlace} </Text>
            </Text>
            <Text style = {{marginBottom: 15}}>
              <Text style={styles.modalInfoHeader}> Start: </Text>
              <Text style={styles.modalText}> {startTime} </Text>
            </Text>
            <Text style = {{marginBottom: 15}}>
              <Text style={styles.modalInfoHeader}> End: </Text>
              <Text style={styles.modalText}> {endTime} </Text>
            </Text>
            <Text style = {{marginBottom: 15}}>
              <Text style={styles.modalInfoHeader}> Number of Attendees: </Text>
              <Text style={styles.modalText}> {numberOfAttendees} </Text>
            </Text>
            <Text style = {{marginBottom: 15}}>
            <Text style={styles.modalInfoHeader}>Description: </Text>
            <Text style={styles.modalText}> {description} </Text>
            </Text>
            <Pressable
              style={[styles.button, styles.buttonClose]}
              onPress={() => setModalVisible(!modalVisible)}>
              <Text style={styles.textStyle}>Close</Text>
            </Pressable>
          </View>
        </View>
      </Modal>

      <Pressable style={styles.container} onPress = {() => {
        setModalVisible(true);
      }}>

        <View style={styles.image}>
          <Image source={image} style={styles.image} />
        </View>

        <View style={isNil(tag) ? 
        styles.sessionInfoContainerNoTag: styles.sessionInfoContainerTag}>
          {!isNil(tag) && <View style={styles.tagContainer}>
            <Text>{tag}</Text>
          </View>}
          <Text style={styles.sessionTitle}>{sessionTitle}</Text>
          <Text style={styles.sessionLocation}> {cardMeetingPlace} </Text>
        </View>

        <View style={styles.sessionButtonContainer}>
          <TouchableOpacity onPress={()=>AddUser(sessionId)} style={styles.sessionButton}>
            <Image source={require("../assets/plusbutton.png")}></Image>
          </TouchableOpacity>
          <Text style={styles.numberOfAttendees}>{numberOfAttendees + " going"}</Text>
        </View>

      </Pressable>
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
    alignItems: "space-evenly"
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
  sessionInfoContainerTag: {
    marginLeft: 10,
  },
  sessionInfoContainerNoTag: {
    marginLeft: 10,
    flexDirection: "column",
    alignItems: "flex-start"
  },
  tagContainer: {
    margin: 5,
    padding: 5,
    backgroundColor: "#03B0A9",
    borderRadius: 10,
    width: 80,
    //maxWidth: "50%",
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
    width: "25%",
    justifyContent: "space-between",
    position: "absolute",
    right: -40,
    top: 12
  },
  sessionButton: {
    position: "absolute",
    top: 0,
    right: 45
  },
  numberOfAttendees: {
    position: "absolute",
    fontSize: 16,
    fontWeight: "bold",
    top: 77,
    right: 50
  },
  centeredView: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 22,
  },
  modalView: {
    margin: 20,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 35,
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
  button: {
    marginTop: 10,
    borderRadius: 10,
    padding: 10,
    elevation: 2,
  },
  buttonClose: {
    backgroundColor: '#5e6364',
  },
  textStyle: {
    color: 'white',
    fontWeight: 'bold',
    textAlign: 'center',
  },
  modalInfoHeader: {
    marginTop: 15,
    marginBottom: 15,
    textAlign: 'center',
    fontWeight: 'bold',
  },
  modalText: {
    marginTop: 15,
    marginBottom: 15,
    textAlign: 'center',
  },
  modalTitle: {
    textDecorationLine: 'underline',
    marginBottom: 30,
    textAlign: 'left',
    fontSize: 20,
    fontWeight: 'bold',
  }
});
