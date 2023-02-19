import { StatusBar } from "expo-status-bar";
import { ScrollView, StyleSheet, Text, View, Button } from "react-native";
import EventCard from "./EventCard";

export default function Categories() {
  return (
    <View>
      <View style={styles.container}>
        <View>
          <Text style={styles.title}>Your Registered Events</Text>
        </View>
        <Button color="green" title="See All" />
      </View>

      <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
        <EventCard backgroundColor="#FFB5E8" title="Event name" subTitle="Description" />
        <EventCard backgroundColor="#86567f" title="Event name" subTitle="Description" />
        <EventCard backgroundColor="#ea0964" title="Event name" subTitle="Description" />
        <EventCard backgroundColor="#0d406a" title="Event name" subTitle="Description" />
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between"
  },
  title: {
    fontSize: 24,
    marginLeft: 10,
    marginTop: 5
  },
});