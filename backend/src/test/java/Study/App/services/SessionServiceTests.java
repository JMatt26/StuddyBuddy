package Study.App.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.SessionRepository;
import Study.App.repository.UserRepository;
import Study.App.service.SessionService;
import jakarta.servlet.http.Part;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTests {
    @Mock
    private SessionRepository sessionRepo;

    @Mock
    private ParticipationRepository participationRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void testIsSessionFullWhenNotFull() {
        
        final Participation participant1 = new Participation();
        participant1.setGoing(true);
        final Participation participant2 = new Participation();
        participant2.setGoing(false);
        Set<Participation> participations = new HashSet<Participation>();
        participations.add(participant1);
        participations.add(participant2);
        List<Participation> participationList = new ArrayList<Participation>();
        participationList.add(participant1);
        participationList.add(participant2);

        final Session session = new Session();
        session.setCapacity(2);
        session.setParticipants(participations);
        
        when(participationRepo.findAllParticipationsBySession(session)).thenAnswer((InvocationOnMock invocation) -> participationList);
        Integer numAttendees = sessionService.getNumAttendees(session);
        Boolean isFull = sessionService.isSessionFull(session);

         assertNotNull(isFull);
         assertEquals(1, numAttendees);
         assertEquals(false, isFull);
    }

    @Test
    public void testIsSessionFullWhenFull() {
        
        final Participation participant1 = new Participation();
        participant1.setGoing(true);
        final Participation participant2 = new Participation();
        participant2.setGoing(true);
        Set<Participation> participations = new HashSet<Participation>();
        participations.add(participant1);
        participations.add(participant2);
        List<Participation> participationList = new ArrayList<Participation>();
        participationList.add(participant1);
        participationList.add(participant2);

        final Session session = new Session();
        session.setCapacity(2);
        session.setParticipants(participations);
        
        when(participationRepo.findAllParticipationsBySession(session)).thenAnswer((InvocationOnMock invocation) -> participationList);
        Integer numAttendees = sessionService.getNumAttendees(session);
        Boolean isFull = sessionService.isSessionFull(session);

        assertNotNull(isFull);
        assertEquals(2, numAttendees);
        assertEquals(true, isFull);
    }

    
}
