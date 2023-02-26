package Study.App.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
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

import Study.App.controller.TOs.CreateSessionTO;
import Study.App.controller.TOs.SessionInformationTO;
import Study.App.controller.TOs.SessionTO;
import Study.App.controller.TOs.UserTO;
import Study.App.model.Session;
import Study.App.repository.SessionRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SessionControllerTest {

    @Autowired
	private TestRestTemplate client;

    @Autowired
    private SessionRepository sessionRepository;

    private static String token1;

    private static String token2;
    
    @BeforeAll
    public void setup() {
        String token1 = getToken1();
        SessionControllerTest.token1 = token1;
        String token2 = getToken2();
        SessionControllerTest.token2 = token2;
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
        // NOTE: For your tests, you can literally copy/paste Step 1 and 2, and adapt step 3 to your needs

        // STEP 1: Creating headers that contain the brearer token
        HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token1);

        // STEP 2: Creating the request with above headers 
		HttpEntity req = new HttpEntity(headers);

        // STEP 2.5: Optional: if you want to send a body with the request, you can do it at this point

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

    @Test
    public void testCreateAndGetSession() {
        int sessionId = testCreateSession();
        testJoinSession(sessionId);
        testGetAllUsersInSession(sessionId);
    }

    // public int testCreateSession() {
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.set("Authorization", "Bearer " + token1);
    //     HttpEntity req = new HttpEntity(new SessionTO(null, false, "league", 10, "tutorial", null, null, null), headers);

    //     ResponseEntity<SessionTO> response = client.postForEntity("/session/createSession", req, SessionTO.class);
    //     assertNotNull(response);
    //     assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
    //     assertNotNull(response.getBody(), "Response has body");
    //     assertEquals("league", response.getBody().title, "Response has correct title");
    //     System.out.println("Finished create session test");
    //     return response.getBody().sessionId;
    // }

    public void testJoinSession(int sessionId){
        HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token2);
        HttpEntity req = new HttpEntity(headers);

        ResponseEntity<SessionTO> response = client.postForEntity(
            "/session/joinSession?sessionId=" + sessionId, 
            req, 
            SessionTO.class
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
    }

    public void testGetAllUsersInSession(int sessionId){
        HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token2);
        HttpEntity req = new HttpEntity(headers);

        ResponseEntity<List<UserTO>> response = client.exchange(
            "/session/getAllUsersInSession?sessionId=" + sessionId,  
            HttpMethod.GET, 
            req, 
            // side note: this is a how you tell the compiler what the type T of the response object is, you wrap it in a new ParameterizedTypeReference<T>() {}
            new ParameterizedTypeReference<List<UserTO>>() {}
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
        assertNotNull(response.getBody(), "Response has body");
        assertEquals("gig", response.getBody().get(1).username, "name is equal");
    }


    public int testCreateSession() {
        HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token1);
        CreateSessionTO body = new CreateSessionTO();
        body.incoming = new SessionTO(
            null, 
            false, 
            "league", 
            10, 
            "tutorial", 
            null, 
            null, 
            null
        );
        List<String> courses = new ArrayList<>();
        courses.add("ECSE-321");
        body.incomingInfo = new SessionInformationTO(
            null,
            "2001-01-01",
            "2001-01-03",
            courses,
            false,
            null,
            null,
            null
        );
		HttpEntity req = new HttpEntity(
            body, 
            headers
        );
        ResponseEntity<SessionTO> response = client.postForEntity("/session/createSession", req, SessionTO.class);
		assertNotNull(response);
        System.out.println("Body " + response.getBody().toString());
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("league", response.getBody().title, "Response has correct title");
		System.out.println("Finished create session test");
        return response.getBody().sessionId;
    }

    private String getToken1() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("parsa", "pass");
		HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = client.postForEntity("/login", req, String.class);
        return response.getBody();
	}

    private String getToken2() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("gig", "pass");
		HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = client.postForEntity("/login", req, String.class);
        return response.getBody();
	}
}
