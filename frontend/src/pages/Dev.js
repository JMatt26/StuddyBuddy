import { View, Text } from "react-native"
import ActiveSessions from "../../components/ActiveSessions"
import Categories from "../../components/Categories"
import CategoryButton from "../../components/CategoryButton"
import { Pressable } from "react-native"
import { AddUser } from "../../helper/AddUser"

export default function Dev(){
    let test = async () => {
        console.log("Hello")
        try {
            await AddUser(1)
        } catch (err) {
            console.log(err)
        }
    }

    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <Categories />
        <Categories />
        <Categories />
        <Pressable onPress={() => test()}><Text>Test</Text></Pressable>
    </View>)
}
