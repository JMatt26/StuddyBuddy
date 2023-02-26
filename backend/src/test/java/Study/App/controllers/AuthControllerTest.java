package Study.App.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import Study.App.controller.TOs.UserTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @Autowired
	private TestRestTemplate client;

    @Test
	public void testCreateAndGetUser() {
		testCreatePerson();
		testGetPerson();
	}

    private UserTO testCreatePerson() {
		ResponseEntity<UserTO> response = client.postForEntity("/signup/", new UserTO(null, "Fouseytube", "fousey", "pass"), UserTO.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Fouseytube", response.getBody().name, "Response has correct name");
		System.out.println("Finished create person test");
		return response.getBody();
	}

	private void testGetPerson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("parsa", "pass");
		HttpEntity req = new HttpEntity(headers);
        ResponseEntity<String> response = client.postForEntity("/login", req, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
