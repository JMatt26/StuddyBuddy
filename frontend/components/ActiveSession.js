import {StyleSheet, Text, View, Button, Image, Pressable } from 'react-native';
import React, { Component } from 'react';
import {AddUser} from "../utils/AddUser";

export default function ActiveSession(location,sessionName,going) {
   
        return (
                <View style={styles.container}>
                    <Pressable><Image style={styles.image} source={{uri: 'https://picsum.photos/200'}}/></Pressable>
                    <Text style={styles.sessionName}>Session Name</Text>
                    <Text style={styles.location}>location</Text>
                    <Text style={styles.going}>going going</Text>

                    <Pressable onPress={()=>AddUser()} style={styles.addButtonContainer}><Image style={styles.addButton} source={require('../assets/buttonIcon.png')}/></Pressable>
                </View>
        );
    };
const styles = StyleSheet.create ({

    container: {
        height: 215,
        width: 168,
        borderRadius: "10%",
        backgroundColor: 'white',
        marginBottom: 8,
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
    addButton: {
        width: 40,
        height: 40,
    },
    addButtonContainer: {
        position: "absolute",
        right : 10,
        bottom : 10,
    }
    
});