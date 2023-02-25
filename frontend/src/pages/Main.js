import {View, Text, Pressable, ScrollView} from "react-native"
import SearchSessionCard from "../../components/SearchSessionCard";
import Categories from "../../components/Categories";
import ActiveSessions from "../../components/ActiveSessions";


export default function Main(){
    return(
        <ScrollView>
        {/*<Text>*/}
        {/*    You are in Main page*/}
        {/*</Text>*/}
        {/*<SearchSessionCard sessionTitle="Session 1" sessionLocation="Location 1" numberOfAttendees="1" tagNames="tag1, tag2, tag3" />*/}
        <Categories />
        <ActiveSessions />
    </ScrollView>
    )
}