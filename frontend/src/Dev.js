import { View, Text, Pressable } from "react-native"
import Categories from "../components/Categories"
import CategoryButton from "../components/CategoryButton"
import { AddUser } from "../helper/AddUser"
export default function Dev(){
    let test = async () => {
        console.log("Hello")
        await AddUser(0)
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