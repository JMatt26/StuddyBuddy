import { View, Text } from "react-native"
import ActiveSessions from "../../components/ActiveSessions"
import Categories from "../../components/Categories"
import CategoryButton from "../../components/CategoryButton"
import { Pressable } from "react-native"
import { AddUser } from "../../utils/AddUser"
import StudySessionCard from "../../components/StudySessionCard"
import assetsObject from "../../assets/assets"
import CreateSessionPage from "../../components/CreateSessionPage"

export default function Dev(){
    

    return(<View>
        <CreateSessionPage/>
    </View>)
}
