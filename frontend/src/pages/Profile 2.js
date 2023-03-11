import { View, Text, TouchableOpacity } from "react-native"
import React, { useContext } from 'react'

import { AuthContext } from "../context/AuthContext"

export default function Profile() {
    const { logout } = useContext(AuthContext);
    return (
        <View>
            <TouchableOpacity onPress={() => { logout() }}>
                <Text>Sign Out</Text>
            </TouchableOpacity>
        </View>

    )
}