import { View, Text } from "react-native"
import ScrollViewComponent from "../components/ScrollViewComponent"
import EventCard from "../components/EventCard"
export default function Dev(){
    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <ScrollViewComponent />
    </View>)
}