package Study.App.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Study.App.controller.TOs.SessionTO;
import Study.App.model.Session;
import Study.App.repository.SessionRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SessionControllerTest {

    @Autowired
	private TestRestTemplate client;

    @Autowired
    private SessionRepository sessionRepository;

    private static String token;
    
    @BeforeAll
    public void setup() {
        String token = getToken();
        SessionControllerTest.token = token;
    }

    @BeforeEach
    public void setupEach() {
        Session session = new Session();
        session.setCapacity(69);
        session.setDescription("description");
        session.setPrivate(false);
        session.setTitle("title");
        sessionRepository.save(session);
    }

    @Test 
    public void testGetSessionByName() {
        // STEP 1: Creating headers that contain the brearer token
        HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

        // STEP 2: Creating the request with above headers 
		HttpEntity req = new HttpEntity(headers);

        // STEP 3: Sending the request to the server to correct URL: note that url contains the reqest parameters
        ResponseEntity<List<SessionTO>> response = client.exchange(
            "/session/getAllSessionsBySessionName?sessionName=title", 
            HttpMethod.GET, 
            req, 
            // side note: this is a how you tell the compiler what the type T of the response object is, you wrap it in a new ParameterizedTypeReference<T>() {}
            new ParameterizedTypeReference<List<SessionTO>>() {}
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
        assertNotNull(response.getBody(), "Response has body");
        assertEquals("title", response.getBody().get(0).title, "title");
    }

    private String getToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("parsa", "pass");
		HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = client.postForEntity("/login", req, String.class);
        return response.getBody();
	}
}
