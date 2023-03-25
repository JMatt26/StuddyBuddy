import { StatusBar } from 'expo-status-bar';
import { ScrollView, StyleSheet, Text, View, Button, Pressable } from 'react-native';

export default function CategoryButton(props) {
    return (
        <View>
            <Pressable backgroundColor={props.backgroundColor} style={styles.button}>
                <Text style={styles.title}>{props.title}</Text>
            </Pressable>
        </View>
    )
}

const styles = StyleSheet.create({
    button: {
        height: 65,
        width: 65,
        borderRadius: "50%",
        fontSize: 16,
        marginTop: 4,
        marginRight: 8,
        marginLeft: 4,
        lineHeight: 65,
        textAlign: "center",
    },
    title: {
        color: 'white',
        textShadowColor: 'black',
        textShadowRadius: 4,
        alignSelf: "center",
        lineHeight: 65,
    }
  });
  