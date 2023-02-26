package Study.App.services;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static final String user1Username = "testUsername1";
    private static final String user2Username = "testUsername2";
    private static final String user1Password = "testPassword1";
    private static final String user2Password = "testPassword2";

    private static final Integer sessionInformationId = 1;

    private ParticipationRole studentRole = ParticipationRole.MEMBER;
    private ParticipationRole adminRole = ParticipationRole.ADMIN;

    @BeforeEach
    void setUpMocks(){
        SessionInformation sessionInfo1 = new SessionInformation();
        List<String> sessionInfo1Tags = new ArrayList<String>();
        sessionInfo1Tags.add("Chess");
        sessionInfo1.setTag(sessionInfo1Tags);

        SessionInformation sessionInfo2 = new SessionInformation();
        List<String> sessionInfo2Tags = new ArrayList<String>();
        sessionInfo2Tags.add("Movies");
        sessionInfo2.setTag(sessionInfo1Tags);

        when(sessionInformationRepository.findAllSessionInformationByTagsIn(anyList()))
        .thenReturn(List.of(sessionInfo1, sessionInfo2));
        Session session1 = new Session();
        Session session2 = new Session();
        Session session3 = new Session();

        session1.setSessionId(1);
        session2.setSessionId(2);
        session3.setSessionId(3);

        session1.setTitle("ECSE 428 study session");
        session2.setTitle("ECSE 428 study session");
        session3.setTitle("ECSE 428 study session");

        session1.setCapacity(10);
        session2.setCapacity(10);
        session3.setCapacity(10);

        session1.setPrivate(false);
        session2.setPrivate(false);
        session3.setPrivate(false);

        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        sessionList.add(session3);

        SessionInformation sessionInformation1 = new SessionInformation();
        sessionInformation1.setSessionInformationId(sessionInformationId);
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

        List<Participation> participationList = new ArrayList<>();
        participationList.add(participation1);
        participationList.add(participation2);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

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

    }
    @Test
    public void testGetSessionsBySessionName() {
        // Setup
        assertEquals(3, sessionService.getSessionsBySessionName("ECSE 428 study session").size());
        assertEquals("ECSE 428 study session", sessionService.getSessionsBySessionName("ECSE 428 study session").get(0).getTitle());

    }
  
    
    @Test
    public void testGetAllSessionsByTag() {
        List<String> testTags = new ArrayList<String>();
        testTags.add("Chess");
        assertEquals(1, sessionService.getAllSessionsByTag(testTags).size());
    }
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
    
   
}
