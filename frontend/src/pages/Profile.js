import { View, Text, TouchableOpacity, StyleSheet, ScrollView, Button } from "react-native"
import { Avatar } from 'react-native-elements';
import React, { useContext } from 'react'
import SessionCard from "../../components/profile/SessionCard"
import FollowingCard from "../../components/profile/FollowingCard"
import ProfileCard from "../../components/profile/ProfileCard"
import request_ressource from "../../utils/fetchApi";
import { isNil } from "../../utils/isNil";

import { AuthContext } from "../context/AuthContext"

export default function Profile() {
    const { logout } = useContext(AuthContext);
    return (
        <ScrollView showsVerticalScrollIndicator={false}>
            <View style={{ flex: 1 }}>
                <ProfileCard name="Parsa Langari" year="U2" university=" McGill University" sessionNo="0" followersNo="0" followingNo="0" />
            </View>
            <View>
                <Text style={styles.bio}>
                    Occaecat irure laboris esse proident excepteur irure ea fugiat. Irure sunt voluptate dolor eu mollit do fugiat mollit ipsum incididunt pariatur.
                </Text>
            </View>
            <View>
                <Text style={styles.subtitle}>
                    My Sessions
                </Text>
                <View style={styles.cards}>
                    <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
                        <SessionCard title="ECSE 211" location="Trottier 5510" />
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
