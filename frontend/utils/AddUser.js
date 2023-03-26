import request_ressource from "./fetchApi";

export async function AddUser(sessionId) {
  try {
      let url = `http://localhost:8080/session/joinSession?sessionId=${sessionId}`;
      const response = await request_ressource(url, "POST");
  } catch (err) {
      console.log(err)
  }
}

