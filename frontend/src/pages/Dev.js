import { View, Text } from "react-native"
import ActiveSessions from "../../components/ActiveSessions"
import Categories from "../../components/Categories"
import CategoryButton from "../../components/CategoryButton"
import { Pressable } from "react-native"
import { AddUser } from "../../utils/AddUser"
import StudySessionCard from "../../components/StudySessionCard"
import assetsObject from "../../assets/assets"
import Search from "../../components/Search"

export default function Dev(){
    

    return(<View>
        <Text style={{ fontSize: 16, fontWeight: "bold", alignSelf: "center" }}>
            ! FOR DEVELOPMENT ONLY !
        </Text>
        <Search/>
        <StudySessionCard tag={"ECSE-324"} sessionTitle={"Studying Session"} sessionLocation={"Trottier"} numberOfAttendees={12} image={assetsObject.mcgillPhoto}/>

    </View>)
}
