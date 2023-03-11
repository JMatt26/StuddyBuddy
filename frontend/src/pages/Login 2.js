import React, { useState, useContext } from "react";
import {
    StyleSheet,
    Text,
    View,
    Button,
    TextInput,
    TouchableOpacity,
} from "react-native";
import { AuthContext } from "../context/AuthContext";

export default function Login({ navigation }) {
    const { login } = useContext(AuthContext);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return (
        <View style={styles.container}>
            <TouchableOpacity style={styles.thirdPartyLogin}>
                <Text style={styles.loginText}>Login with Facebook </Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.thirdPartyLogin}>
                <Text style={styles.loginText}>Login with Google </Text>
            </TouchableOpacity>
            <View style={styles.middleTextContainer}>
                <View style={styles.middleTextLines} />
                <View>
                    <Text style={styles.middleText}>OR</Text>
                </View>
                <View style={styles.middleTextLines} />
            </View>
            <View>
                <Text style={styles.existingAccount}>Sign in with an existing account</Text>
            </View>
            <View style={styles.inputView}>
                <TextInput
                    style={styles.TextInput}
                    placeholder="Email"
                    placeholderTextColor="#003f5c"
                    value={email}
                    onChangeText={(email) => setEmail(email)}
                />
            </View>
            <View style={styles.inputView}>
                <TextInput
                    style={styles.TextInput}
                    placeholder="Password"
                    placeholderTextColor="#003f5c"
                    secureTextEntry={true}
                    value={password}
                    onChangeText={(password) => setPassword(password)}
                />
            </View>
            <TouchableOpacity>
                <Text style={styles.forgot_button}>Forgot Password?</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.loginBtn} onPress={() => { { login(email, password) } }}>
                <Text style={styles.loginText}>LOGIN</Text>
            </TouchableOpacity>
            <View
                style={{
                    flexDirection: 'row',
                    justifyContent: 'center',
                    marginTop: 30,
                }}>
                <Text>New to the app?</Text>
                <TouchableOpacity onPress={() => navigation.navigate('SignUp')}>
                    <Text style={{ color: '#7ecd32', fontWeight: '700' }}> Sign Up</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#fff",
        alignItems: "center",
        paddingLeft: 20,
        paddingRight: 20,
        paddingBottom: 40,
        paddingTop: 40,
    },
    middleTextContainer: {
        flexDirection: 'row',
        alignItems: 'center',
    },
    middleTextLines: {
        flex: 1,
        height: 1,
        backgroundColor: 'grey',
    },
    middleText: {
        width: 60,
        textAlign: 'center',
    },
    existingAccount: {
        marginTop: 10,
        marginBottom: 20,
        textAlign: 'center',
    },
    thirdPartyLogin: {
        backgroundColor: "#d2fed2",
        borderStyle: "solid",
        borderRadius: 30,
        width: "70%",
        height: 45,
        marginBottom: 15,
        marginTop: 10,
        alignItems: "center",
        justifyContent: "center",
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
        alignItems: "center",
    },
    TextThirdParty: {
        color: '#006a00',
    },
    loginText: {
        color: '#006a00',
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
