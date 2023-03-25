import request_ressource from "./fetchApi";

export async function helper(sessionId) {
  let url = `http://localhost:8080/session/joinSession?sessionId=${sessionId}`;

  const response = await request_ressource(url, "POST");

}

export async function AddUser(sessionId) {
  try {
      await helper(sessionId)
  } catch (err) {
      console.log(err)
  }
}

