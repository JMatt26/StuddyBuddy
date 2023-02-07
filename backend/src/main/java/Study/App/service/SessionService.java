package Study.App.service;

import org.springframework.stereotype.Service;

import Study.App.model.Session;
import Study.App.repository.ParticipantRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipantRepository participantRepository;
    private SessionInformationRepository sessionInformationRepository;

    public SessionService(SessionRepository sessionRepository, ParticipantRepository participantRepository, SessionInformationRepository sessionInformationRepository) {
        this.sessionRepository = sessionRepository;
        this.participantRepository = participantRepository;
        this.sessionInformationRepository = sessionInformationRepository;
    }

    @Transactional
     public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, Integer attendees, Integer participantId, Integer sessionInformationId){
        Session session = new Session();

        var participant = participantRepository.findParticipantByParticipantId(participantId);
        var sessionInformation = sessionInformationRepository.findSessionInformationBySessionInformationId(sessionInformationId);

        participant.setParticipantId(participantId);
        sessionInformation.setSessionInformationId(sessionInformationId);

        session.setPrivate(isPrivate);
        session.setAttendees(attendees);
        session.setCapacity(capacity);
        session.setDescription(description);
        session.setParticipant(participant);
        session.setSessionInformation(sessionInformation);
        session.setTitle(title);

        return sessionRepository.save(session);
     }
}
