import React, { Component } from "react";
import { useState } from "react";

import request_ressource from "../../utils/fetchApi";
import ActiveSession from "../../components/ActiveSession";
import { StyleSheet, View, Text, Button } from "react-native";


export default function Main(){
    const [data, setData] = useState([]);
    const [status, setStatus] = useState("");
    let url = 'http://localhost:8080/session/getAllSessions';

    let response = null;
    try{
        response = request_ressource(url);
        setStatus(response.status);
        response = response.body.json();
        console.log(response);
        setData(response);
    } catch(e){
        console.log(e.message);
    }

    let listSessions = data.map(element => {
        return(
            <View>
                <ActiveSession
                    sessionName={element.incoming.title}
                    location={"Somewhere"}
                    attendanceNbr={element.incoming.numberOfAttendees}>
                </ActiveSession>
            </View>
        )
    });  
}