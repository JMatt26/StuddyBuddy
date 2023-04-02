import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Login from '../pages/Login'
import SignUp from '../pages/SignUp';
import AllSessionsComponent from '../../components/AllSessionsComponent';

const Stack = createNativeStackNavigator();

const AuthStack = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Login" component={Login} />
            <Stack.Screen name="SignUp" component={SignUp} />
            <Stack.Screen name="AllSessions" component={AllSessionsComponent} />
        </Stack.Navigator>
    );
};

export default AuthStack;