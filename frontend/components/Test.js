import { Text, TextInput, View, StyleSheet, Switch, TouchableOpacity } from "react-native";

export default function Test(props){

    function pressedButton(){
        console.log("pressed")
    }

    return(
        <View style={styles.page}>
            <Text  style={{backgroundColor: props.color}}>
                Hello {props.name} {props.lastName * 2}
            </Text>
            <TouchableOpacity
            onPress={pressedButton}>
                <Text>
                    Button
                </Text>
            </TouchableOpacity>


        </View>
    )
}

const styles = StyleSheet.create({

    page:{
        backgroundColor: "#fff",
    
    }


})