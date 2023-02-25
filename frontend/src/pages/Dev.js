import { View, Text } from "react-native"
import ActiveSessions from "../../components/ActiveSessions"
import Categories from "../../components/Categories"
import CategoryButton from "../../components/CategoryButton"
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