import axios from "axios";

export function AddUser(sessionId) {
  axios
    .post("http://localhost:8080/joinSession", { sessionId: sessionId })
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
}
