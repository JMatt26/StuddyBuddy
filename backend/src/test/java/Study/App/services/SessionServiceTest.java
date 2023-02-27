package Study.App.services;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.service.SessionService;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
	private SessionRepository sessionRepository;

	@Mock
	private SessionInformationRepository sessionInformationRepository;

    @InjectMocks
	private SessionService sessionService;

	private Session sessOne;
	private Session sessTwo;
	
	@BeforeEach
	public void createObjects() {

		// create necessary objects for test
		// This will Mock the content of your database
		// Basically a fake database system

		Session sessionOne = new Session();
		sessionOne.setPrivate(false);
		sessionOne.setCapacity(25);
		sessionOne.setTitle("ECSE324");
		this.sessOne = sessionOne;

		Session sessionTwo = new Session();
		sessionTwo.setPrivate(false);
		sessionTwo.setCapacity(78);
		sessionTwo.setTitle("FACC300");
		this.sessTwo = sessionTwo;
		
		// active session
		SessionInformation desc = new SessionInformation();
		Date startOne = new Date(2023,1,25);
		desc.setStartTime(startOne);
		Date endOne = new Date(2023,3,1);
		desc.setEndTime(endOne);
		desc.setOnline(false);
		this.sessOne.setSessionInformation(desc);

		// upcoming session
		SessionInformation descTwo = new SessionInformation();
		Date startTwo = new Date(2023,3,16);
		descTwo.setStartTime(startTwo);
		Date endTwo = new Date(2023,3,17);
		descTwo.setEndTime(endTwo);
		descTwo.setOnline(false);
		this.sessTwo.setSessionInformation(descTwo);

		List<SessionInformation> sessInformation = new ArrayList<SessionInformation> ();
		sessInformation.add(desc);
		sessInformation.add(descTwo);
		List <Session> sessions = new ArrayList<Session>();
		sessions.add(sessionOne);
		sessions.add(sessionTwo);

		when(sessionInformationRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> sessInformation);
		when(sessionRepository.findAllSessionBySessionInformation(any(SessionInformation.class))).thenAnswer((InvocationOnMock invocation) -> sessions);
		
	}

	@Test
	public void testGetAllUpcomingSessions(){
		List<Session> temp = null;
		try {
			temp = sessionService.getAllUpcomingSessions();
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertNotNull(temp);
		
	}

}
