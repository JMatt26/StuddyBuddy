import { StatusBar } from 'expo-status-bar';
import { ScrollView, StyleSheet, Text, View, Button, Pressable, Image } from 'react-native';

export default function FollowingCard(props) {
    return (
        <View>
            <Image style={styles.button} source={{ uri: props.imageUri }} />
            <Text style={styles.name}>{props.name}</Text>
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
    name: {
        textShadowColor: 'black',
        alignSelf: "center",
        marginTop: 5
    }
});
