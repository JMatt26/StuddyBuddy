import { StatusBar } from 'expo-status-bar';
import { TouchableOpacity, StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from "@react-navigation/native";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Main from "./src/Main";
import Login from "./src/Login";
import Events from "./src/Events";
import SignUp from "./src/SignUp";

const Tab = createBottomTabNavigator();

export default function App() {
  return (
    <NavigationContainer>
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
        <Tab.Screen name="DEVELOPMENT">
          {() => <Login />}
        </Tab.Screen>
        <Tab.Screen name="Events">
          {() => <Events />}
        </Tab.Screen>
      </Tab.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({

});
