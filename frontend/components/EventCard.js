import { useLinkProps } from "@react-navigation/native";
import { StatusBar } from "expo-status-bar";
import { ScrollView, StyleSheet, Text, View, Button, Pressable } from "react-native";

export default function EventCard(props){
    return (
        <View>
            <Pressable backgroundColor = {props.backgroundColor} style={styles.button}>
                <Text style={styles.title}>{props.title}</Text>
                <Text style={styles.subTitle}>{props.subTitle}</Text>
            </Pressable>
        </View>
    )
}

const styles = StyleSheet.create({
    button: {
        height: 100,
        width: 200,
        borderRadius: "15%",
        fontSize: 16,   
        marginTop: 4,
        marginRight: 8,
        marginLeft: 4,
        lineHeight: 65,
        textAlign: "left",
    },
    title: {
        color: 'white',
        textShadowColor: 'black',
        textShadowRadius: 4,
        //alignSelf: "center",
        lineHeight: 0,
        paddingTop: 40,
        fontSize: 22,
    },
    subTitle: {
        color: 'white',
        textShadowColor: 'black',
        textShadowRadius: 4,
        verticalAlign: "bottom",
        lineHeight: 30,
        fontSize: 16,
    }
});