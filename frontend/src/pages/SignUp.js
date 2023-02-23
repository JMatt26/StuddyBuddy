import React, { useState } from "react";
import {
    StyleSheet,
    Text,
    View,
    TextInput,
    TouchableOpacity,
} from "react-native";

export default function SignUp() {
    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    return (
        <View style={styles.container}>
            <View>
                <Text style={styles.title}>Create an account today!</Text>
            </View>
            <View style={styles.inputView}>
                <TextInput
                    style={styles.TextInput}
                    placeholder="Name"
                    placeholderTextColor="#003f5c"
                    onChangeText={(name) => setName(name)}
                />
            </View>
            <View style={styles.inputView}>
                <TextInput
                    style={styles.TextInput}
                    placeholder="Username"
                    placeholderTextColor="#003f5c"
                    secureTextEntry={true}
                    onChangeText={(username) => setUsername(username)}
                />
            </View>
            <View style={styles.inputView}>
                <TextInput
                    style={styles.TextInput}
                    placeholder="Password"
                    placeholderTextColor="#003f5c"
                    onChangeText={(password) => setPassword(password)}
                />
            </View>
            <TouchableOpacity style={styles.loginBtn}>
                <Text style={styles.loginText}>Sign Up</Text>
            </TouchableOpacity>
        </View>
    );
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#fff",
        alignItems: "center",
        paddingTop: 50,
    },
    title: {
        backgroundColor: "#fff",
        alignItems: "center",
        paddingBottom: 50,
        fontSize: 20
    },
    inputView: {
        opacity: .3,
        borderWidth: 1,
        borderStyle: 'solid',
        borderRadius: 10,
        width: "70%",
        height: 45,
        marginBottom: 20,
        alignItems: "center",
    },
    TextInput: {
        height: 50,
        flex: 1,
        padding: 10,
    },
    forgot_button: {
        height: 30,
        marginBottom: 30,
    },
    loginBtn: {
        width: "80%",
        borderRadius: 25,
        height: 50,
        alignItems: "center",
        justifyContent: "center",
        marginTop: 40,
        backgroundColor: "#7ecd32",
    },
});
