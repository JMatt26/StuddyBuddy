import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

export async function AddUser(sessionId) {
  try {
    let jwt = await AsyncStorage.getItem("userToken");
    const headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${jwt}`,
    };
    const res = await axios
      .post(
        "http://localhost:8080/joinSession" + `?sessionId=${sessionId}`,
        {},
        {
          headers: headers,
        }
      )
  } catch (err) {
    console.log(err);
  }

  // if (res.status == 401) {
  //   console.log("JWT Token Expired")
  // } else if (res.status == 200) {
  //   console.log("All Good")
  // } else {
  //   console.log(res.status)
  // }

  // axios
  //   .post("http://localhost:8080/joinSession", null, { params: {sessionId}})
  //   .then(function (response) {
  //     console.log(response);
  //   })
  //   .catch(function (error) {
  //     console.log(error);
  //   });
}
