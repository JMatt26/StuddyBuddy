package Study.App.service;

import org.springframework.stereotype.Service;

import Study.App.model.Participant;
import Study.App.model.Session;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.ParticipantRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private SessionInformationRepository sessionInformationRepository;

    public SessionService(SessionRepository sessionRepository, ParticipantRepository participantRepository, SessionInformationRepository sessionInformationRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionInformationRepository = sessionInformationRepository;
    }

    @Transactional
     public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, Integer attendees, String adminUsername, Integer sessionInformationId){
        Session session = new Session();

        Participant admin = new Participant();
        admin.setUsername(adminUsername);
        admin.setRole(ParticipationRole.ADMIN);

        var sessionInformation = sessionInformationRepository.findSessionInformationBySessionInformationId(sessionInformationId);

        session.setPrivate(isPrivate);
        session.setAttendees(attendees);
        session.setCapacity(capacity);
        session.setDescription(description);
        session.setParticipant(admin);
        session.setSessionInformation(sessionInformation);
        session.setTitle(title);

        return sessionRepository.save(session);
     }
}
