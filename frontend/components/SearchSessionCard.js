import { StyleSheet, View, Text } from "react-native"
import { isNil } from "../utils/isNil"

export default function SearchSessoionCard({sessionTitle, sessionLocation, numberOfAttendees, tagNames }){

    return (<>
        <View style={styles.container}>
            {/* Tag name */}
            { !isNil(tagNames) && <View style={styles.tag}>

            </View>}
            {/* Session name */}
            <View style={styles.name}>
                <Text>
                    {sessionTitle}
                </Text>
            </View>
            {/* Session Location */}
            <View style={styles.location}>
                <Text>
                    {sessionLocation}
                </Text>
            </View>
            {/* Num attendees */}
            <View styles={styles.number}>
                <Text>
                    {numberOfAttendees}
                </Text>
            </View>
            {/* Button to join */}
            <Pressable style={styles.button} onPress={() => alert(`Assigning you to session:  + ${sessionTitle}`)}>

            </Pressable>
        </View>
    </>)
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'start',
    },
    tag: {
        backgroundColor: '#03B0A9',
        borderRadius: 25,
        height: '7%',
        maxHeight: 35,
        width: 'auto'
    },
    name: {

    },
    location: {

    },
    number: {

    },
    button: {

    }
})