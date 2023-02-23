import { View, Text } from "react-native"
import ActiveSessions from "../components/ActiveSessions"
import Categories from "../components/Categories"
import CategoryButton from "../components/CategoryButton"
export default function Dev(){
    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <Categories />
        <ActiveSessions />
    </View>)
}