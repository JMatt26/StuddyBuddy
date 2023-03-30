import { StatusBar } from 'expo-status-bar';
import { ScrollView, StyleSheet, Text, View, Button, Pressable } from 'react-native';

export default function SessionCard(props) {
    return (
        <View>
            <Pressable backgroundColor="grey" style={styles.button}>
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
    },
    title: {
        color: 'white',
        textShadowColor: 'black',
        alignSelf: "center",
    },
    text: {
        position: "absolute",
        alignSelf: "center",
        padding: 10
    }

});