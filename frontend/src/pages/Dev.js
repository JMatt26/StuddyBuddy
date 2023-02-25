import { View, Text } from "react-native"
import ActiveSessions from "../../components/ActiveSessions"
import Categories from "../../components/Categories"
import CategoryButton from "../../components/CategoryButton"
import { Pressable } from "react-native"
import { AddUser } from "../../utils/AddUser"

export default function Dev(){
    

    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <Categories />
        <Categories />
        <Categories />
        <Pressable onPress={() => AddUser(1)}><Text>Test</Text></Pressable>
    </View>)
}
