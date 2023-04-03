import { StyleSheet , View, Text} from "react-native";
import { TextInput } from "react-native-paper";
import {Search} from "react-native-feather";

export default function SearchInput({error = false, label ,state, setState, isMultiLine = false , textInputStyle ={}, textStyle = {} , placeholder, icon }){
    return (
        <View style={[{
            marginTop: 10,
            marginBottom: 5,
            flexDirection: 'row',
            alignItems: 'center',
            marginHorizontal: 12,
            backgroundColor: 'white',
            borderRadius: 25
        }, textInputStyle]}>
            {icon}
            {(label && label != "") ?
            ( <Text style={[{
                    width: '80%',
                    textAlign: 'left',
                    marginBottom: 6,
                    fontWeight: '200',
                    fontSize: 14,
                    color: !error ? '#FFF' : '#f94449',
                }, textStyle]}> {label} </Text> ) : null}
            <TextInput
                value={state}
                onChangeText={(state) => {
                    setState(state);
                }}
                placeholder={placeholder}
                placeholderTextColor="grey"
                style={[styles.input,
                    error ? {
                        borderStyle: 'solid',
                        borderWidth: 2,
                        borderColor: '#f94449',
                    } : null
                ]}
                activeUnderlineColor='transparent'
                underlineColor="transparent"
                selectionColor="#041830"
                multiline={isMultiLine}
            />
        </View>
    )
}


const styles = StyleSheet.create({
    input: {
        fontSize: 16,
        height: 50,
        width: "100%",
        borderRadius: 12,
        borderTopLeftRadius: 12,
        borderTopRightRadius: 12,
        backgroundColor: 'none',
    },
});
