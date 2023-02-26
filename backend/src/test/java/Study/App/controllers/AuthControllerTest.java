package Study.App.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import Study.App.controller.TOs.UserTO;
import Study.App.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @Autowired
	private TestRestTemplate client;

	@Autowired
	private UserRepository userRepository;

    @BeforeAll
	@AfterAll
	public void clearDatabase() {
		userRepository.deleteAll();
	}

    @Test
	public void testCreateAndGetUser() {
		testCreatePerson();
		testGetPerson();
	}

    private UserTO testCreatePerson() {
		ResponseEntity<UserTO> response = client.postForEntity("/signup/", new UserTO(null, "Fouseytube", "fousey", "pass"), UserTO.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Fouseytube", response.getBody().name, "Response has correct name");

		return response.getBody();
	}

	private void testGetPerson() {
		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("username", "parsa");
        request.set("password", "pass");
        request.set("grant_type", "password");
        @SuppressWarnings("unchecked")
        String response = this.client.withBasicAuth("parsa", "password")
            .postForObject("/login", request, String.class);
        
        assertNotNull(response);
	}
}
