import { useState } from "react";
import { Text, TextInput, View, StyleSheet, Switch, TouchableOpacity, ScrollView } from "react-native";
import DropDownPicker from 'react-native-dropdown-picker';
import CalendarPicker from 'react-native-calendar-picker';
import request_ressource from "../utils/fetchApi";
import InputListAdd from "./InputListAdd";

export default function CreateSessionPage(){

    const [startDateOpen, setStartDateOpen] = useState(false);
    const [startHourOpen, setStartHourOpen] = useState(false);
    const [startMinOpen, setStartMinOpen] = useState(false);
    const [courseList,setCourseList] = useState([]);
    const [tagList,setTagList] = useState([]);

    const[titleValue, setTitleValue] = useState(null);
    const[capacityValue, setCapacityValue] = useState(null);
    const [descriptionValue, setDescriptionValue] = useState(null);
    const [isOnline, setIsOnline] = useState(false);
    const [locationValue, setLocationValue] = useState(null)
    const [isPrivate, setIsPrivate] = useState(false);
    const [startDateValue, setStartDateValue] = useState(new Date(Date.now()));
    const [startHourValue, setStartHourValue] = useState(1);
    const [startMinValue, setStartMinValue] = useState(0);
    const [sessionDurationValue, setSessionDurationValue] = useState(null);

    const [validDate, setValidDate] = useState(true);

    const toggleCal1 = () => setStartDateOpen(previousState => !previousState);
    const selectedStartDate = function(date){
        setStartDateOpen(false)
        setStartDateValue(new Date(date))
    }
  
    const toggleOnlineSwitch = () => setIsOnline(previousState => !previousState);
    const toggleSwitch = () => setIsPrivate(previousState => !previousState);



   
    let hourI=[];
    for (var i = 0; i < 24; i++) {
        hourI.push({label: i, value: i, key: i});
    }
    const [hourItems, setHourItems] = useState(hourI);

    const [minItems, setMinItems] = useState([
        {label: '00' , value: 0, key: 0},
        {label: 15, value: 15, key: 1},
        {label: 30, value: 30, key: 2},
        {label: 45, value: 45, key: 3},
    ]);

    function addZero(num){
        if(num < 10){
            return "0"+num
        }
        else{
            return num;
        }
    }



   async function onCreateSession() {
        //console.log(titleValue,capacityValue,descriptionValue,locationValue)
        
        //let sessionInfo = SessionInformationTO(null, String startTime, String endTime, String course, Boolean isOnline, List<String> materialUrl, sessionId, locationId) 

        startDateValue.setHours(startHourValue)
        startDateValue.setMinutes(startMinValue)
        let endDateValue = new Date(startDateValue)
        endDateValue.setHours((startDateValue.getHours() + Number(sessionDurationValue)));
        
        let startTime = startDateValue.getFullYear()+ "-" + addZero(startDateValue.getMonth()+1) + "-" + addZero(startDateValue.getDate()) + " " + addZero(startDateValue.getHours()) + ":" + addZero(startDateValue.getMinutes())+ ":" + addZero(startDateValue.getSeconds())
        let endTime = endDateValue.getFullYear()+ "-" + addZero(endDateValue.getMonth()+1) + "-" + addZero(endDateValue.getDate()) + " " + addZero(endDateValue.getHours()) + ":" + addZero(endDateValue.getMinutes())+ ":" + addZero(endDateValue.getSeconds())
        
        console.log(tagList);
        console.log(courseList);

        let sessionInformationTO = {sessionInformationId: null, startTime: startTime, endTime: endTime, courses: courseList, tags: tagList,  isOnline: isOnline, materialUrl: null, sessionId: null, locationId: null, location: locationValue, adminUsername: null}
        let sessionTO = {sessionId: null , isPrivate: isPrivate, title: titleValue, capacity: capacityValue, description: descriptionValue, numberOfAttendees: 1 , participationIds: null, sessionInformationId: null} 

        let createSessionTO = {incoming: sessionTO, incomingInfo: sessionInformationTO}

        let url = `http://localhost:8080/session/createSession`;
        try {
            let response = await request_ressource(url, "POST", {} , createSessionTO);
            console.log(JSON.stringify(response));
        } catch (error) {
            console.log(JSON.stringify(response));
        }
        
    }

      
    return(
        <ScrollView style={styles.page}>
            <Text style={styles.createSession}>
                Create Session
            </Text>
            <Text style={styles.timeText}> Title </Text>
            <TextInput style={styles.title} placeholder="Title" value={titleValue} onChangeText={setTitleValue}/>
            
            <Text style={styles.timeText}> Session Courses </Text> 
            <InputListAdd placeholderText={"Courses"} buttonText={"Add"} elementList={courseList} setElementList={setCourseList}/>
            
            <Text style={styles.timeText}> Session Description </Text> 
            <TextInput style={styles.description} placeholder="Session Description" value={descriptionValue} onChangeText={setDescriptionValue} multiline={true}/>
            
            <Text style={styles.timeText}> Session Tags </Text> 
            <InputListAdd placeholderText={"Tags"} buttonText={"Add"} elementList={tagList} setElementList={setTagList}/>
            
            <Text style={styles.timeText}> Session Capacity </Text> 
            <TextInput style={styles.capacity} placeholder="Max Capacity(limit 99)" maxLength={2} value={capacityValue} onChangeText={setCapacityValue}/>
            
            <Text style={styles.timeText}> Is the Session Taking Place In-Person? </Text>
            <Switch
                trackColor={{false: '#767577', true: '#54B175'}}
                thumbColor={'#fff'}
                ios_backgroundColor="#3e3e3e"
                onValueChange={toggleOnlineSwitch}
                value={isOnline}
            />
            
            <TextInput style={[styles.title, styles.addMargin ,isOnline ? {display: "inline"}:{display: "none"}]} placeholder="Location" value={locationValue} onChangeText={setLocationValue}/> 
            <Text style={styles.timeText}> Is the Session Private? </Text>
            <Switch
                trackColor={{false: '#767577', true: '#54B175'}}
                thumbColor={'#fff'}
                ios_backgroundColor="#3e3e3e"
                onValueChange={toggleSwitch}
                value={isPrivate}
            />
            {/* private public */}
            <Text style={styles.timeText}>Start Date and Time</Text>
            <View style={styles.dropDown1}> 
                <TouchableOpacity /* StartDate */
                    style={styles.date} 
                    onPress={toggleCal1}
                > 
                    <Text style={styles.dateText}> {startDateValue.toDateString()}</Text>
                </TouchableOpacity>
                <Text style={styles.timeDotText}> </Text>
                <DropDownPicker /* StartHour */
                    labelField="label"
                    valueField="value"
                    indexField="key"
                    listMode="SCROLLVIEW"
                    style={styles.dropDownItem}
                    open={startHourOpen}
                    value={startHourValue}
                    items={hourItems}
                    setOpen={setStartHourOpen}
                    setValue={setStartHourValue}
                    setItems={setHourItems}
                    containerStyle={{
                        width: '20%',
                        flex:5,
                        
                    }}
                />
                <Text style={styles.timeDotText}> :</Text>
                <DropDownPicker /* StartMin */
                    labelField="label"
                    valueField="value"
                    indexField="key"
                    listMode="SCROLLVIEW"
                    style={styles.dropDownItem}
                    open={startMinOpen}
                    value={startMinValue}
                    items={minItems}
                    setOpen={setStartMinOpen}
                    setValue={setStartMinValue}
                    setItems={setMinItems}
                    containerStyle={{
                        width: '20%',
                        flex:5,

                    }}
                />
            </View>
            <View style={startDateOpen ? {display: "block"} : {display: "none"}}>
            <CalendarPicker
                onDateChange={selectedStartDate}
                selectedDayColor={"#54B175"}
                selectedDayTextColor={"#FFF"}
            />  
            </View>
            <Text style={styles.timeText}> Session Duration</Text> 
            <TextInput style={styles.capacity} placeholder="Duration(99 hours)" maxLength={2} value={sessionDurationValue} onChangeText={setSessionDurationValue}/>

                <TouchableOpacity
                style={styles.button}
                title = {"Create Session"}
                onPress={onCreateSession}
                >
                <Text style={styles.buttonText}>
                    Create Session
                </Text>
                </TouchableOpacity>
            </ScrollView>

    )
}
const styles = StyleSheet.create({
    page: {
        backgroundColor: "#fff",
        height: "100%",
        paddingHorizontal: "5%",
    },
    createSession:{
        fontSize: "35px",
        fontWeight: "bold",
        marginTop: 30,  
    },
    title:{
        fontSize: 15,
        borderColor: "#000",
        borderWidth: 1,
        width: "80%",
        paddingLeft: 10,
        paddingVertical: 15,
        borderRadius: 8,
        backgroundColor: "#fff",
    },
    description:{
        fontSize: 15,
        borderColor: "#000",
        borderWidth: 1,
        width: "100%",
        height: "10%",
        paddingLeft: 10,
        paddingTop: 15,
        paddingVertical: 15,
        borderRadius: 8,
        backgroundColor: "#fff",  
    },
    capacity:{
        fontSize: 15,
        borderColor: "#000",
        borderWidth: 1,
        width: "50%",
        paddingLeft: 10,
        paddingVertical: 15,
        borderRadius: 8,
        backgroundColor: "#fff",
    },
    dropDown1: {
        flexDirection: 'row',
        width: '100%',
        borderColor: "AEAEB2",
        justifyContent: "space-between",
        zIndex: 500,
    },
    dropDown2: {
        flexDirection: 'row',
        width: '100%',
        borderColor: "AEAEB2",
        justifyContent: "space-between",
        zIndex: 100
    },
    timeText:{
        fontSize: 17,
        paddingTop: 20,
        paddingBottom: 10,

    },
    date:{
        borderWidth: 1,
        borderColor:"#000",
        height: 50,
        flex:7,
        borderRadius: 8,
        alignItems: "center",
    },
    dateText:{
        lineHeight: 45,
        alignItems: "center",
    },
    timeDotText:{
        fontSize: 15,
        fontWeight: "bold",
        //borderWidth: 1,
        alignItems: "center",
        paddingLeft: 5,
        flex:1,
        lineHeight: 45,
    },
    error: {
        fontSize:16,
        marginTop: 20,
        color: "#FF4343",
        fontWeight: "bold",
        alignSelf: "center",
    },
    button: {
        backgroundColor: "#54B175",
        alignItems: "center",
        paddingVertical: 20,
        marginVertical: 20,
        borderRadius: 8,
        marginBottom: 170

    },
    buttonText: {
        color: "#fff",
        fontSize: 24,
        fontWeight: "500",
    },
    addMargin: {
        marginTop: 10
    }
})