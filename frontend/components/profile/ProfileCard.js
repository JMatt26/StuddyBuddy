import { StatusBar } from 'expo-status-bar';
import { ScrollView, StyleSheet, Text, View, Button, Pressable, Image } from 'react-native';

export default function ProfileCard(props) {
    return (
        <View style={styles.box}>
            <View style={[styles.card, styles.shadowProp]}>
                <View style={[styles.circle, styles.shadowProp]} />
                <Text style={styles.name}>
                    {props.name}
                    <Text style={styles.year}>({props.year})</Text>
                </Text>
                <Text style={styles.university}>
                    {props.university}
                </Text>
                <View style={styles.statsContainer}>
                    <View style={styles.statsBox}>
                        <Text style={[styles.text, { fontSize: 24 }]}>{props.sessionNo}</Text>
                        <Text style={[styles.text, styles.subText]}>Sessions</Text>
                    </View>
                    <View style={[styles.statsBox, { borderColor: "#DFD8C8", borderLeftWidth: 1, borderRightWidth: 1 }]}>
                        <Text style={[styles.text, { fontSize: 24 }]}>{props.followersNo}</Text>
                        <Text style={[styles.text, styles.subText]}>Followers</Text>
                    </View>
                    <View style={styles.statsBox}>
                        <Text style={[styles.text, { fontSize: 24 }]}>{props.followingNo}</Text>
                        <Text style={[styles.text, styles.subText]}>Following</Text>
                    </View>
                </View>
            </View>
        </View >
    )
}

const styles = StyleSheet.create({
    heading: {
        fontSize: 18,
        fontWeight: '600',
        marginBottom: 13,
    },
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
    year: {
        paddingHorizontal: 5,
        fontSize: 15,
    }
});