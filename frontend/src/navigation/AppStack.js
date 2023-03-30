import { View, Text } from 'react-native';
import React from 'react'

import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import Main from "../pages/Main";
import Events from "../pages/Events";
import Profile from "../pages/Profile";
import Dev from "../pages/Dev";
import AllSessions from '../pages/AllSessions';

import AntDesign from 'react-native-vector-icons/AntDesign';
import Entypo from 'react-native-vector-icons/Entypo';
import Feather from 'react-native-vector-icons/Feather';

const Tab = createBottomTabNavigator();

export default function AppStack() {
    return (
        <Tab.Navigator screenOptions={({ route }) => ({
            tabBarIcon: ({ focused, color, size }) => {
              let iconName;
  
              if (route.name === 'Main') {
                iconName = 'home';
              } else if (route.name === 'Create Session') {
                iconName = 'pencil';
                return <Entypo name={iconName} size={size} color={color} />;
              } else if (route.name === 'Profile') {
                iconName = 'user';
                return <Feather name={iconName} size={size} color={color} />;
              } else if (route.name = 'My Events') {
                iconName = 'calendar';
              }
  
              // You can return any component that you like here!
              return <AntDesign name={iconName} size={size} color={color} />;
            },
            tabBarActiveTintColor: 'green',
            tabBarInactiveTintColor: 'gray',
            headerShown: true,
            tabBarStyle: { height: '10%' },
            style: {
                backgroundColor: '#FFFFFF',
                borderTopLeftRadius: 50,
                borderTopRightRadius: 50,
            }
          })}>
            <Tab.Screen name='Main'>
                {() => 
                    <Main />
                }
            </Tab.Screen>
            <Tab.Screen name='Create Session'>
                {() => <Dev />}
            </Tab.Screen>
            <Tab.Screen name="My Events">
                {() => <AllSessions />}
            </Tab.Screen>
            <Tab.Screen name="Profile">
                {() => <Profile />}
            </Tab.Screen>
        </Tab.Navigator>
    )
};