import AsyncStorage from "@react-native-async-storage/async-storage";

export default async function request_ressource(
    request_url,
    request_method = "GET",
    request_headers = {},
    request_body = null
  ) {
    try {
        //getting important tokens from req
        let jwt = await AsyncStorage.getItem("userToken");
  
        // appending the correct headers 
        request_headers['Authorization'] = `Bearer ${jwt}`;
        if (request_method == "POST" || request_method == "PUT" || request_method == "DELETE") {
            request_headers['Content-Type'] = "application/json";
        }
  
        // making request 
        console.log(`requesting ressource ${request_url}\n`);
  
        let response = await fetch(request_url, {
            method: request_method,
            headers: request_headers,
            body: request_method !== "GET" ? JSON.stringify(request_body) : null
        });
  
        // bad requests
        if (response.status >= 400) {
            console.warn(" request failed with status : " + response.status);
            return;
        }
  
        console.log(`request sucessfull with status  + response.status`)
        return {
            status: response.status,
            body: response
        }
    } catch (err) {
        return {
            status: 500,
            error: err 
        }
    }
  }