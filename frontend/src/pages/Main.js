import {StyleSheet, Text, View, Button, Image, Pressable } from 'react-native';
import ActiveSession from "../../components/ActiveSession"
import ActiveSessions from "../../components/ActiveSessions";
import React from "react";
export default function Main(){
    return(<View>
        <Text>
            You are in Main page

        </Text>
        <ActiveSessions />
    </View>)
}