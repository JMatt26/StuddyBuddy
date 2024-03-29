import { View, Text } from 'react-native';
import React from 'react'

import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import MainIndex from "../pages/MainIndex";
import Events from "../pages/Events";
import Profile from "../pages/Profile";
import Dev from "../pages/Dev";
import MySessions from '../pages/MySessions';

import AntDesign from 'react-native-vector-icons/AntDesign';
import Entypo from 'react-native-vector-icons/Entypo';
import Feather from 'react-native-vector-icons/Feather';
import EvilIcons from 'react-native-vector-icons/EvilIcons';
import SearchSessions from '../pages/SearchSessions';

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
              } else if (route.name === 'My Events') {
                iconName = 'calendar';
              } else if (route.name === 'Search') {
                iconName = 'search';
                return <EvilIcons name={iconName} size={size} color={color} />;
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
                    <MainIndex />
                }
            </Tab.Screen>
            <Tab.Screen name='Create Session'>
                {() => <Dev />}
            </Tab.Screen>
            <Tab.Screen name="My Events">
                {() => <MySessions />}
            </Tab.Screen>
            <Tab.Screen name="Profile">
                {() => <Profile />}
            </Tab.Screen>

        </Tab.Navigator>
    )
};