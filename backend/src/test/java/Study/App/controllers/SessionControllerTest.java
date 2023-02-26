package Study.App.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;

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
    }

    @Test
	public void testCreateAndGetSession() {
        testCreateSession();
	}

    private SessionTO testCreateSession() {
        ResponseEntity<SessionTO> response = client.postForEntity("/createSession", new SessionTO(1234, false, "league", 10, "tutorial", 6, null, null), SessionTO.class);
		assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(1234, response.getBody().sessionId, "Response has correct Id");
		System.out.println("Finished create session test");
        return response.getBody();
    }

    private String getToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("parsa", "pass");
		HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = client.postForEntity("/login", req, String.class);
        return response.getBody();
	}
}
