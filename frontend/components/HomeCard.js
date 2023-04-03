import {StyleSheet, Text, View, Button, Image, TouchableOpacity } from 'react-native';
import React, { Component } from 'react';
import {AddUser} from "../utils/AddUser";
export default function HomeCard(props) {
        return (
            <View style={styles.container}>
                    <Image 
                    style={styles.image} source={{uri: 'https://picsum.photos/200'}}/>
                    <Text style={styles.sessionName}>{props.sessionName}</Text>
                    <Text style={styles.location}>{props.location}</Text>
                    <Text style={styles.going}>{props.attendanceNbr} going</Text>
                    <TouchableOpacity onPress={()=>AddUser(props.sessionId)} style={styles.sessionButton}>
                        <Image source={require("../assets/plusbutton.png")}></Image>
                    </TouchableOpacity>
            </View>
        );
    };
const styles = StyleSheet.create ({
    container: {
        height: 215,
        width: 145,
        borderRadius: "10%",
        backgroundColor: 'white',
        marginBottom: 8,
        marginHorizontal: 5
    },
    sessionName: {
        color: "black",
        marginHorizontal: 7,
        fontSize: 17,
    },
    image: {
        borderRadius: "10%",
        height: 110,
        margin: 7,
    },
    location: {
        marginHorizontal: 7,
        paddingTop: 5,
        paddingBottom: 20,
        color: "#808080",
        fontSize: 12,
    },
    going: {
        marginHorizontal: 7,
        fontSize: 20,
    },
    sessionButton: {
        position: 'absolute',
        bottom: 7,
        right: 7,
      },
});