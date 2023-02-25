import AsyncStorage from '@react-native-async-storage/async-storage';
import React, { createContext, useState, useEffect } from 'react';
import { Pressable} from "react-native";
import axios from 'axios';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoading, setIsLoading] = useState(false);
    const [userToken, setUserToken] = useState(null);

    const login = (username, password) => {
        setIsLoading(true);
        axios.post('http://localhost:8080/login', {}, {
            auth: {
                username,
                password
            }
        })
            .then(res => {
                let userInfo = res.data;
                setUserToken(userInfo);
                AsyncStorage.setItem('userToken', userInfo);
                console.log('User Token: ' + userInfo);
            })
            .catch(e => {
                console.log(`Login error ${e}`);
                return alert("Wrong username or password");
            });
        setIsLoading(false);
    }

    const logout = () => {
        setIsLoading(true);
        setUserToken(null);
        AsyncStorage.removeItem('userToken');
        setIsLoading(false);
    }

    const isLoggedIn = async () => {
        try {
            setIsLoading(true);
            let userToken = await AsyncStorage.getItem('userToken');
            setUserToken(userToken);
            setIsLoading(false);
        } catch (e) {
            console.log(`isLogged in error ${e}`)
        }
    }

    useEffect(() => {
        isLoggedIn();
    }, [])
    return (
        <AuthContext.Provider value={{ login, logout, isLoading, userToken }} >
            {children}
        </AuthContext.Provider >
    )
}