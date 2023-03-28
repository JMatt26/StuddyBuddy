import { View, Text } from 'react-native';
import React from 'react'

import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import Main from "../pages/Main";
import Events from "../pages/Events";
import Profile from "../pages/Profile";
import Dev from "../pages/Dev";
import AllSessions from '../pages/AllSessions';

const Tab = createBottomTabNavigator();

export default function AppStack() {
    return (
        <Tab.Navigator screenOptions={{
            headerShown: true,
            tabBarStyle: { height: '13%' },
            style: {
                backgroundColor: '#FFFFFF',
                borderTopLeftRadius: 50,
                borderTopRightRadius: 50,
            }
        }}>
            <Tab.Screen name='Main'>
                {() => <Main />}
            </Tab.Screen>
            <Tab.Screen name='DEVELOPMENT'>
                {() => <Dev />}
            </Tab.Screen>
            <Tab.Screen name="Events">
                {() => <AllSessions />}
            </Tab.Screen>
            <Tab.Screen name="Profile">
                {() => <Profile />}
            </Tab.Screen>
        </Tab.Navigator>
    )
};