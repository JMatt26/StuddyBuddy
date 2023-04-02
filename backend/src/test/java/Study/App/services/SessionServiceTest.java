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

import Study.App.controller.TOs.CreateSessionTO;
import Study.App.model.*;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.*;
import Study.App.service.ParticipationService;
import Study.App.service.SessionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;


import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.service.SessionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

	@Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationService participationService;

    @Mock
    private SessionInformationRepository sessionInformationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserInformationRepository userInformationRepository;

    @Mock
    private LocationRepository locationRepository;
   

    private static final String user1Username = "testUsername1";
    private static final String user2Username = "testUsername2";
    private static final String user1Password = "testPassword1";
    private static final String user2Password = "testPassword2";

    private static final Integer sessionInformationId = 1;

    private static final List courses = new ArrayList<String>();
    private static final List tags = new ArrayList<String>();
    private static final Date startTime = new Date(123,2,5);
    private static final Date endTime = new Date(123,7,3);
    private static final Date startTimeTwo = new Date(123,5,12);
    private static final Date endTimeTwo = new Date(123,6,12);
    private static final Boolean isOnline = false;

    private ParticipationRole studentRole = ParticipationRole.MEMBER;
    private ParticipationRole adminRole = ParticipationRole.ADMIN;
	private Session sessOne;
	private Session sessTwo;

    @BeforeEach
    void setUpMocks(){
        SessionInformation sessionInfo1 = new SessionInformation();
        SessionInformation sessionInfo2 = new SessionInformation();
        SessionInformation sessionInfo3 = new SessionInformation();
        SessionInformation sessionInfo4 = new SessionInformation();
        SessionInformation sessionInfo5 = new SessionInformation();

        List<String> sessionInfo1Tags = new ArrayList<String>();
        List<String> sessionInfo1Courses = new ArrayList<String>();
        List<String> sessionInfo2Tags = new ArrayList<String>();
        List<String> sessionInfo2Courses = new ArrayList<String>();
        List<String> sessionInfo3Tags = new ArrayList<String>();
        List<String> sessionInfo3Courses = new ArrayList<String>();
        List<String> sessionInfo4Tags = new ArrayList<String>();
        List<String> sessionInfo4Courses = new ArrayList<String>();
        List<String> sessionInfo5Tags = new ArrayList<String>();
        List<String> sessionInfo5Courses = new ArrayList<String>();

        sessionInfo1Tags.add("Movies");
        sessionInfo1Tags.add("Chess");
        sessionInfo1Tags.add("Music");
        sessionInfo1Courses.add("ECSE-324");

        sessionInfo2Tags.add("Music");
        sessionInfo2Courses.add("ECSE-428");

        sessionInfo3Tags.add("Movies");
        sessionInfo3Tags.add("Music");
        sessionInfo3Courses.add("ECSE-324");

        sessionInfo4Tags.add("Chess");
        sessionInfo4Tags.add("Silent");
        sessionInfo4Courses.add("ECSE-324");

        sessionInfo5Tags.add("Silent");
        sessionInfo5Courses.add("COMP-251");

        sessionInfo1.setTags(sessionInfo1Tags);
        sessionInfo1.setStartTime(startTime);
        sessionInfo1.setEndTime(endTime);
        sessionInfo1.setCourses(sessionInfo1Courses);
        
        sessionInfo2.setTags(sessionInfo2Tags);
        sessionInfo2.setStartTime(startTimeTwo);
        sessionInfo2.setEndTime(endTimeTwo);
        sessionInfo2.setCourses(sessionInfo2Courses);

        sessionInfo3.setTags(sessionInfo3Tags);
        sessionInfo3.setCourses(sessionInfo3Courses);

        sessionInfo4.setTags(sessionInfo4Tags);
        sessionInfo4.setCourses(sessionInfo4Courses);

        sessionInfo5.setTags(sessionInfo5Tags);
        sessionInfo5.setCourses(sessionInfo5Courses);
        
        sessionInfo1.setBuildingName("Building1");
        
        List<SessionInformation> sessionInfos = new ArrayList<>();
        
        sessionInfos.add(sessionInfo1);
        sessionInfos.add(sessionInfo2);
        sessionInfos.add(sessionInfo3);
        sessionInfos.add(sessionInfo4);

        List<Session> sessionList = new ArrayList<>();

        courses.add("ECSE-324");
        Session session1 = new Session();
        Session session2 = new Session();
        Session session3 = new Session();
        Session session4 = new Session();
        Session session5 = new Session();

        session1.setSessionId(1);
        session2.setSessionId(2);
        session3.setSessionId(3);
        session4.setSessionId(4);
        session5.setSessionId(5);

        session1.setTitle("ECSE 428 study session");
        session2.setTitle("ECSE 428 study session");
        session3.setTitle("ECSE 428 study session");
        session4.setTitle("ECSE 428 study session");
        session5.setTitle("ECSE 428 study session");

        session1.setCapacity(10);
        session2.setCapacity(10);
        session3.setCapacity(10);
        session4.setCapacity(10);
        session5.setCapacity(10);

        session1.setPrivate(false);
        session2.setPrivate(false);
        session3.setPrivate(false);
        session4.setPrivate(false);
        session5.setPrivate(false);

        session1.setSessionInformation(sessionInfo1);
        session2.setSessionInformation(sessionInfo2);
        session3.setSessionInformation(sessionInfo3);
        session4.setSessionInformation(sessionInfo4);
        session5.setSessionInformation(sessionInfo5);

        //sessionInfo1.setSession(session1);

        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);
        sessionList.add(session4);
        sessionList.add(session5);

        Location location1 = new Location();
        location1.setStreetAddress("123 Address1");
        location1.setCity("Montreal");
        location1.setProvince("Quebec");
        location1.setPostalCode("H3T 1M8");
        location1.setBuildingName("Building1");
        location1.setRoomNumber("Room1");
    
        

        SessionInformation sessionInformation1 = new SessionInformation();
        sessionInformation1.setSessionInformationId(sessionInformationId);
        
        
        sessionInformation1.setCourses(courses);
        sessionInformation1.setStartTime(startTime);
        sessionInformation1.setEndTime(endTime);
        sessionInformation1.setOnline(isOnline);
        sessionInformation1.setLocation(location1);
        sessionInformation1.setSession(session1);


        //Mock users
        User user1 = new User();
        User user2 = new User();

        user1.setUsername(user1Username);
        user1.setPassword(user1Password);
        user1.setUserId(1);
        user2.setUsername(user2Username);
        user2.setPassword(user2Password);
        user2.setUserId(2);

        UserInformation userInformation1 = new UserInformation();
        UserInformation userInformation2 = new UserInformation();

        userInformation1.setUser(user1);
        userInformation2.setUser(user2);

        Participation participation1 = new Participation();
        participation1.setParticipationId(1);
        participation1.setRole(adminRole);
        participation1.setSession(session1);
        participation1.setUserInformation(userInformation1);
        participation1.setIsGoing(true);

        Participation participation2 = new Participation();
        participation2.setParticipationId(2);
        participation2.setRole(studentRole);
        participation2.setSession(session1);
        participation2.setUserInformation(userInformation2);
        participation2.setIsGoing(true);

        List<Participation> participationList = new ArrayList<Participation>();
        participationList.add(participation1);
        participationList.add(participation2);

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        lenient().when(sessionInformationRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> List.of(sessionInfo1, sessionInfo2, sessionInfo3, sessionInfo4, sessionInfo5));

        //lenient().when(sessionInformationRepository.findAllSessionInformationByBuildingName("Building1")).thenAnswer((InvocationOnMock invocation) -> sessionInfos);
        
        lenient().when(sessionRepository.findSessionBySessionInformation(sessionInfo1)).thenAnswer((InvocationOnMock invocation) -> session1);
        lenient().when(sessionRepository.findSessionBySessionInformation(sessionInfo2)).thenAnswer((InvocationOnMock invocation) -> session2);
        lenient().when(sessionRepository.findSessionBySessionInformation(sessionInfo3)).thenAnswer((InvocationOnMock invocation) -> session3);
        lenient().when(sessionRepository.findSessionBySessionInformation(sessionInfo4)).thenAnswer((InvocationOnMock invocation) -> session4);
        lenient().when(sessionRepository.findSessionBySessionInformation(sessionInfo5)).thenAnswer((InvocationOnMock invocation) -> session5);

        lenient().when(locationRepository.findLocationByLocationid(1)).thenAnswer((InvocationOnMock invocation) -> location1);

        lenient().when(sessionRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> sessionList);
        lenient().when(sessionRepository.findAllSessionByTitle("ECSE 428 study session")).thenAnswer((InvocationOnMock invocation) -> sessionList);

        lenient().when(sessionRepository.findSessionBySessionId(1)).thenAnswer((InvocationOnMock invocation) -> session1);
        lenient().when(sessionRepository.findSessionBySessionId(2)).thenAnswer((InvocationOnMock invocation) -> session2);
        lenient().when(sessionRepository.findSessionBySessionId(3)).thenAnswer((InvocationOnMock invocation) -> session3);


        lenient().when(userRepository.findUserByUsername(user1Username)).thenAnswer((InvocationOnMock invocation) -> user1);
        lenient().when(userRepository.findUserByUsername(user2Username)).thenAnswer((InvocationOnMock invocation) -> user2);
        lenient().when(participationRepository.findAllParticipationBySessionSessionId(1)).thenAnswer((InvocationOnMock invocation) -> participationList);

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(sessionRepository.save(any(Session.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(sessionInformationRepository.save(any(SessionInformation.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(participationRepository.save(any(Participation.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(userRepository.save(any(User.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(userInformationRepository.save(any(UserInformation.class))).thenAnswer(returnParameterAsAnswer);
		
    }

    // Letao
    @Test
    public void testGetSessionsBySessionName() {
        // Setup
        assertEquals(5, sessionService.getSessionsBySessionName("ECSE 428 study session").size());
        //assertEquals("ECSE 428 study session", sessionService.getSessionsBySessionName("ECSE 428 study session").get(0).getTitle());
    }
  
    @Test
    public void testGetAllSessionsByTag() {
        List<String> testTags = new ArrayList<String>();
        testTags.add("Chess");

        assertEquals(2, sessionService.getSessionsByTag(testTags).size());
    }

    //Nicholas
    @Test
    public void testGetAllSessionsByCourse() {
        List<String> testCourses = new ArrayList<String>();
        testCourses.add("ECSE-324");

        assertEquals(3, sessionService.getSessionsByCourse(testCourses).size());
    }

    // Letao
    @Test
    public void testCreateSession() {
        String title = "ECSE 428 study session";
        int capacity = 10;
        boolean isPrivate = false;
        String description = "ECSE 428 study session its gna be fun!";

        Session session = null;
        try {
            session = sessionService.createSession(isPrivate, title, capacity, description, user1Username);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(session);
        assertEquals(title, session.getTitle());
    }
    // Letao
    @Test
    public void testDeleteSession(){
        Boolean session = null;
        try {
            session = sessionService.deleteSession(1, user1Username);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(session);
    }


    // Letao
    @Test
    public void testGetAllUsersInSession(){
        List<User> userList = null;
        try {
            userList = sessionService.getAllUsersInSession(1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(userList);
        assertEquals(2, userList.size());
    }
    
    //Saviru
    @Test
    public void testGetAllActiveSessions(){
        List<CreateSessionTO> sessList = null;
        try {
            sessList = sessionService.getAllActiveSessions();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(sessList);
        assertEquals(1, sessList.size());
    }

    //Saviru
    @Test
    public void testGetAllUpcomingSessions(){
        List<CreateSessionTO> sessList = null;
        try {
            sessList = sessionService.getAllUpcomingSessions();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(sessList);
        assertEquals(1, sessList.size());
    }

    // Letao
    @Test
    public void testAddInfoToSession(){
        SessionInformation sessionInformation = null;
        try {
            sessionInformation = sessionService.addInfoToSession(1, startTime, endTime, courses, tags, isOnline, null, 1, null);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(sessionInformation);
    }

    // Letao
    @Test
    public void testAddInfoToInvalidSession(){
        SessionInformation sessionInformation = null;
        String error = null;
        try {
            sessionInformation = sessionService.addInfoToSession(4, startTime, endTime, courses, tags, isOnline, null, 1, null);
        } catch (Exception e) {
            error = e.getMessage();

        }
        assertNull(sessionInformation);
        assertEquals("Session not found", error);
    }

    // Sadek
    @Test
    public void testGetSessionsByBuildingName() {
        final String buildingName = "Building1";
        Session testSession = new Session();
        SessionInformation testSessionInfo = new SessionInformation();

        testSession.setSessionInformation(testSessionInfo);
        testSessionInfo.setSession(testSession);

        final ArrayList<SessionInformation> testSessionInfos = new ArrayList<SessionInformation>();
        testSessionInfos.add(testSessionInfo);

        when(sessionInformationRepository.findAllSessionInformationByBuildingName(buildingName)).thenAnswer((InvocationOnMock invocation) -> testSessionInfos);


        assertEquals(1, sessionService.getSessionsByBuildingName("Building1").size());

        
    
    }
}
