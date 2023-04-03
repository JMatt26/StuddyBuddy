import { View, Text, TouchableOpacity, StyleSheet, ScrollView, Button } from "react-native"
import { Avatar } from 'react-native-elements';
import React, { useContext, useState, useEffect, useReducer } from 'react'
import SessionCard from "../../components/profile/SessionCard"
import FollowingCard from "../../components/profile/FollowingCard"
import ProfileCard from "../../components/profile/ProfileCard"
import request_ressource from "../../utils/fetchApi";
import { isNil } from "../../utils/isNil";

import { AuthContext } from "../context/AuthContext"

export default function Profile() {
    const { logout, username } = useContext(AuthContext);
    const [data, setData] = useState([]);
    const [status, setStatus] = useState("");

    async function getSessionsOfUser() {
        let url = "";
        url = `http://localhost:8080/session/getAllSessions`;

        let response = null;
        try {
            response = await request_ressource(url, "GET");
            setStatus(response.status);
            response = await response.body.json();
            console.log(response);
            setData(response);
        } catch (e) {
            console.log(e);
        }
    }


    useEffect(() => {
        getSessionsOfUser();
    }, []);

    let renderSessions = "";
    renderSessions = data.map((event, index) => {
        return (
            <View key={index}>
                <SessionCard
                    title={event.incoming.title}
                    attendees={event.incoming.numberOfAttendees}
                />
            </View>)
    }
    );

    const renderSessionsCount = renderSessions.length;


    return (
        <ScrollView showsVerticalScrollIndicator={false}>
            <View style={{ flex: 1 }}>
                <ProfileCard name={username} year="U2" university=" McGill University" sessionNo={renderSessionsCount} followersNo="0" followingNo="0" />
            </View>

            <View>
                <Text style={styles.subtitle}>
                    My Sessions
                </Text>
                <View style={styles.cards}>
                    <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
                        {renderSessions}
                    </ScrollView>
                </View>
            </View>
            <View>
                <Text style={styles.subtitle}>
                    Following
                </Text>
                <View style={styles.cards}>
                    <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
                        <FollowingCard imageUri='https://i.pravatar.cc/300' name="Fousey" />
                        <FollowingCard imageUri='https://i.pravatar.cc/300' name="Fousey" />
                        <FollowingCard imageUri='https://i.pravatar.cc/300' name="Fousey" />
                        <FollowingCard imageUri="https://i.pravatar.cc/300" name="Fousey" />
                        <FollowingCard imageUri="https://i.pravatar.cc/300" name="Fousey" />
                    </ScrollView>
                </View>
            </View>
            <Button
                onPress={logout}
                title="Log Out"
                color="red"
            />
        </ScrollView >

    )
}

const styles = StyleSheet.create({
    box: {
        alignItems: "center",
        marginTop: 40
    },
    card: {
        alignItems: "center",
        backgroundColor: 'white',
        borderRadius: 8,
        paddingTop: 45,
        paddingBottom: 20,
        width: '80%',
        marginVertical: 10,
        flex: 1
    },
    circle: {
        marginTop: -80,
        width: 100,
        height: 100,
        borderRadius: 100 / 2,
        backgroundColor: "grey",
    },
    shadowProp: {
        alignItems: "center",
        shadowColor: '#171717',
        shadowOffset: { width: -2, height: 4 },
        shadowOpacity: 0.2,
        shadowRadius: 3,
    },
    name: {
        padding: 10,
        fontSize: 20,
        fontWeight: 'bold'
    },
    icons: {
        position: 'absolute', //Here is the trick
        bottom: 0, //Here is the trick
        width: '100%',
        height: '30%',
        flex: 2,
        justifyContent: 'flex-end',
        borderWidth: 8,
        borderBottomLeftRadius: 8,
        borderBottomRightRadius: 8
    },
    statsContainer: {
        flexDirection: "row",
        alignSelf: "center",
        marginTop: 32
    },
    statsBox: {
        alignItems: "center",
        flex: 1
    },
    bio: {
        marginVertical: 10,
        marginHorizontal: 20,
        fontFamily: 'Times New Roman',
        lineHeight: 20
    },
    subtitle: {
        alignText: 'left',
        marginTop: 15,
        marginHorizontal: 20,
        fontSize: 15,
        fontWeight: 'bold'
    },
    cards: {
        marginHorizontal: 20,
        marginVertical: 10,
    }

});
