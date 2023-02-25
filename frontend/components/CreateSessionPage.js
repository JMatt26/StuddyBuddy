import { useState } from "react";
import { Text, TextInput, View, StyleSheet, Switch, TouchableOpacity } from "react-native";
import DropDownPicker from 'react-native-dropdown-picker';


export default function CreateSessionPage(){


    const [startHourOpen, setStartHourOpen] = useState(false);
    const [endHourOpen, setEndHourOpen] = useState(false);
    const [startMinOpen, setStartMinOpen] = useState(false);
    const [endMinOpen, setEndMinOpen] = useState(false);
    const [startAmOpen, setStartAmOpen] = useState(false);
    const [endAmOpen, setEndAmOpen] = useState(false);
    const [isPrivate, setIsPrivate] = useState(false);
    const toggleSwitch = () => setIsPrivate(previousState => !previousState);

    const [startHourValue, setStartHourValue] = useState(1);
    const [endHourValue, setEndHourValue] = useState(1);
    const [startMinValue, setStartMinValue] = useState(0);
    const [endMinValue, setEndMinValue] = useState(0);
    const [startAmValue, setStartAmValue] = useState(0);
    const [endAmValue, setEndAmValue] = useState(0);

    const [hourItems, setHourItems] = useState([
        {label: 1, value: 1},
        {label: 2, value: 2},
        {label: 3, value: 3},
        {label: 4, value: 4},
        {label: 5, value: 5},
        {label: 6, value: 6},
        {label: 7, value: 7},
        {label: 8, value: 8},
        {label: 9, value: 9},
        {label: 10, value: 10},
        {label: 11, value: 11},
        {label: 12, value: 12},
    ]);
    const [minItems, setMinItems] = useState([
        {label: '00' , value: 0},
        {label: 15, value: 15},
        {label: 30, value: 30},
        {label: 45, value: 45},
    ]);

    const [amItems, setAmItems] = useState([
        {label: 'AM' , value: 0},
        {label: 'PM', value: 1},
    ]);


    function onCreateSession() {
        
    }

      
    return(
        <View style={styles.page}>
            <Text style={styles.createSession}>
                Create Session
            </Text>
            <TextInput style={styles.title} placeholder="Title"/> 
            <TextInput style={styles.capacity} placeholder="Max Capacity(limit 99)" maxLength={2}/>
            <Text style={styles.timeText}> Is the Session Private? </Text>
            <Switch
                trackColor={{false: '#767577', true: '#54B175'}}
                thumbColor={'#fff'}
                ios_backgroundColor="#3e3e3e"
                onValueChange={toggleSwitch}
                value={isPrivate}
            />
            {/* private public */}
            <Text style={styles.timeText}>Start Time</Text>
            <View style={styles.dropDown1}> 
                <DropDownPicker /* Hour */
                    style={styles.dropDownItem}
                    open={startHourOpen}
                    value={startHourValue}
                    items={hourItems}
                    setOpen={setStartHourOpen}
                    setValue={setStartHourValue}
                    setItems={setHourItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,
                        
                    }}
                />
                <Text style={styles.timeDotText}> :</Text>
                <DropDownPicker /* Min */
                    style={styles.dropDownItem}
                    open={startMinOpen}
                    value={startMinValue}
                    items={minItems}
                    setOpen={setStartMinOpen}
                    setValue={setStartMinValue}
                    setItems={setMinItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,

                    }}
                />
                <Text style={styles.timeDotText}> </Text>
                <DropDownPicker /* Am */
                    style={styles.dropDownItem}
                    open={startAmOpen}
                    value={startAmValue}
                    items={amItems}
                    setOpen={setStartAmOpen}
                    setValue={setStartAmValue}
                    setItems={setAmItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,
                        
                    }}
                    dropDownContainerStyle={{
                        borderColor: "AEAEB2",
                        zIndex: 1000
                    }}
                />
                </View>
                <Text style={styles.timeText}>End Time</Text>
                <View style={styles.dropDown2}> 
                <DropDownPicker /* Hour */
                    style={styles.dropDownItem}
                    open={endHourOpen}
                    value={endHourValue}
                    items={hourItems}
                    setOpen={setEndHourOpen}
                    setValue={setEndHourValue}
                    setItems={setHourItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,
                    }}
                />
                <Text style={styles.timeDotText}> :</Text>
                <DropDownPicker /* Min */
                    style={styles.dropDownItem}
                    open={endMinOpen}
                    value={endMinValue}
                    items={minItems}
                    setOpen={setEndMinOpen}
                    setValue={setEndMinValue}
                    setItems={setMinItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,
                    }}
                />
                <Text style={styles.timeDotText}> </Text>
                <DropDownPicker /* Am */
                    style={styles.dropDownItem}
                    open={endAmOpen}
                    value={endAmValue}
                    items={amItems}
                    setOpen={setEndAmOpen}
                    setValue={setEndAmValue}
                    setItems={setAmItems}
                    containerStyle={{
                        width: '25%',
                        flex:7,
                    }}
                    dropDownContainerStyle={{
                        borderColor: "AEAEB2",
                        zIndex: 500,
                        //marginHorizontal: "10%"
                    }}
                />
                </View>
                <TouchableOpacity
                style={styles.button}
                title = {"Create Session"}
                onPress={onCreateSession}
                >
                <Text style={styles.buttonText}>
                    Create Session
                </Text>
                </TouchableOpacity>
            </View>

    )
}
const styles = StyleSheet.create({
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
    dropDownItem: {
        
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
        borderRadius: 10,
        backgroundColor: "#fff",
        marginTop: 25,
    },
    capacity:{
        fontSize: 15,
        borderColor: "#000",
        borderWidth: 1,
        width: "50%",
        paddingLeft: 10,
        paddingVertical: 15,
        borderRadius: 10,
        backgroundColor: "#fff",
        marginTop: 20,
    },
    page: {
        backgroundColor: "#fff",
        height: "100%",
        paddingHorizontal: "5%"
    },
    timeText:{
        fontSize: 17,
        paddingTop: 20,
        paddingBottom: 10,

    },
    button: {
        backgroundColor: "#54B175",
        alignItems: "center",
        paddingVertical: 20,
        marginVertical: 20,
        borderRadius: 8
    },
    buttonText: {
        color: "#fff",
        fontSize: 24,
        
    },
    timeDotText:{
        fontSize: 15,
        fontWeight: "bold",
        //borderWidth: 1,
        alignItems: "center",
        paddingLeft: 5,
        flex:1,
        lineHeight: 45,
    }



})