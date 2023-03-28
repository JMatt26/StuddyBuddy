import { useState, useEffect } from "react";
import { StyleSheet, TextInput, TouchableOpacity, View, Text } from "react-native";


//

export default function InputListAdd({ elementList, setElementList, buttonText, placeholderText }){
    

    const[elementValue, setElementValue] = useState(null);

 
    function addElement(){
        if (elementValue && elementValue.trim()){
                let newEls = [...elementList];
                newEls.push(elementValue.trim());
                setElementList(newEls);
            }
            setElementValue(null);
            
            console.log(elementList)
    }
    

    function deleteKeyElement(key){
        let newEls = [...elementList];
        newEls.splice(key,1);
        setElementList(newEls);
    }

    function formatElementValue(){
    const list = elementList.map((e,index) =>
            <View key={index} style={styles.elementContainer}>
                <View style={styles.elementView}>
                    <Text style={styles.elementText}>
                        {e}
                    </Text>
                </View>
                <TouchableOpacity onPress={() => deleteKeyElement(index)} style={styles.deleteButton}>
                    <Text style={styles.deleteText}>
                        x
                    </Text>
                </TouchableOpacity>
            </View>
            )
    return list
    
}
    


    return (
    <View>
        <View style={styles.inputContainer}>
            <TextInput style={styles.input} placeholder={placeholderText} value={elementValue} onChangeText={setElementValue}/>
            <TouchableOpacity style={styles.button} onPress={() => addElement()}>
                <Text style={styles.buttonText}>
                {buttonText}
                </Text>
            </TouchableOpacity>
        </View>
        <View style={styles.elementList}>
            {formatElementValue()}
        </View>
    </View>

    )
    
}

const styles = StyleSheet.create({
    input:{
        flex: 5,
        fontSize: 15,
        borderColor: "#000",
        borderWidth: 1,
        height: 50,
        paddingLeft: 10,
        paddingTop: 15,
        paddingVertical: 15,
        borderRadius: 8,
        backgroundColor: "#fff",  
        marginRight: 10,
    },
    inputContainer:{
        flexDirection: "row",
        width: '100%',
        justifyContent: "space-between",
        paddingTop: 10,
    },
    button: {
        flex: 2,
        backgroundColor: "#54B175",
        alignItems: "center",
        paddingVertical: 15,
        height: 50,
        borderRadius: 8,
    },
    buttonText: {
        color: "#fff",
        fontSize: 16,
        fontWeight: "500",
    },
    elementList:{
        paddingTop: 10,
        flexDirection: "row",
        justifyContent: "space-evenly",
        flexWrap: "wrap",
    },
    elementContainer:{
        borderRadius: 8,
        marginTop: 10,
        alignSelf: 'center',
        backgroundColor: "#fff",
        justifyContent: 'center',
        flexDirection: "row",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
    },
    elementView:{
        alignSelf: 'center',
        justifyContent: 'center',
        borderRadius: 8,
    },
    elementText:{
        paddingVertical: 5,
        paddingHorizontal: 10,
        borderRadius: 8,
    },
    deleteButton:{
        overflow: "hidden",
        borderRadius: 8,
    },
    deleteText:{
        color: "#fff",
        backgroundColor: "#FF4343",
        fontSize: 16,
        paddingVertical: 5,
        paddingHorizontal: 10,
        margin: 0,
        borderRadius: 8,
    }



})