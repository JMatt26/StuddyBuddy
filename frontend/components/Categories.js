import { StatusBar } from "expo-status-bar";
import { ScrollView, StyleSheet, Text, View, Button } from "react-native";
import CategoryButton from "./CategoryButton";

export default function Categories() {
  return (
    <View>
      <View style={styles.container}>
        <View>
          <Text style={styles.title}>Categories</Text>
        </View>
        <Button color="green" title="See All" />
      </View>

      <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
        <CategoryButton backgroundColor="#FFB5E8" title="ECSE" />
        <CategoryButton backgroundColor="#F9E1E0" title="MECH" />
        <CategoryButton backgroundColor="#9799BA" title="Anime" />
        <CategoryButton backgroundColor="#8AC0DE" title="Chess" />
        <CategoryButton backgroundColor="#FFB5E8" title="Arts" />
        <CategoryButton backgroundColor="#F9E1E0" title="Comp. Sci" />
        <CategoryButton backgroundColor="#9799BA" title="Hello" />
        <CategoryButton backgroundColor="#8AC0DE" title="Hello" />
        <CategoryButton backgroundColor="#FFB5E8" title="Hello" />
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
