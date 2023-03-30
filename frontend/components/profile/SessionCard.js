import { StatusBar } from 'expo-status-bar';
import { ScrollView, StyleSheet, Text, View, Button, Pressable } from 'react-native';

export default function SessionCard(props) {
    return (
        <View>
            <Pressable style={styles.button}>
                <View style={styles.text}>
                    <Text style={styles.title}>{props.title}</Text>
                    <Text style={styles.location}>{props.location}</Text>
                </View>
            </Pressable >
        </View >
    )
}

const styles = StyleSheet.create({
    button: {
        height: 65,
        width: 120,
        borderRadius: "10%",
        fontSize: 16,
        lineHeight: 65,
        textAlign: "center",
        backgroundColor: "#7ecd32"
    },
    title: {
        alignSelf: "center",
        fontSize: 20
    },
    location: {
        fontSize: 10,
        alignSelf: "center",
        lineHeight: 30
    },
    text: {
        position: "absolute",
        alignSelf: "center",
        padding: 10
    }

});