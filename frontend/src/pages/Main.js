import React, { Component } from "react";
import request_ressource from "../../utils/fetchApi";
import ActiveSession from "../../components/ActiveSession";
import { StyleSheet, View, Text, Button } from "react-native";
import Searchbar from '../../components/Searchbar';

export default function Main(){
    // let url = `http://localhost:8080/session/getAllActiveSessions`;
    // const response = request_ressource(url);
    return(
        // response.array.forEach(element => {
        //     <ActiveSession sessionName="${element.name}" location="${element.location}" ></ActiveSession>
        // })
        <View>
            <Searchbar />
        </View>
        
    )
}