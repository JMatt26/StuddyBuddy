package Study.App.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.model.User;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.repository.UserRepository;
import Study.exceptions.IncorrectDataException;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipationRepository participationRepository;
    private SessionInformationRepository sessionInformationRepository;
    private UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, ParticipationRepository participationRepository, SessionInformationRepository sessionInformationRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, String username, Integer sessionInformationId) {

        Session session = new Session();

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IncorrectDataException("User not found", HttpStatus.BAD_REQUEST);
        }
        Participation sessionParticipation = new Participation();
        sessionParticipation.setRole(ParticipationRole.ADMIN);
        sessionParticipation.setIsGoing(true);
        sessionParticipation.setSession(session);
        sessionParticipation.setUserInformation(user.getUserInformation());



        SessionInformation sessionInformation = sessionInformationRepository
                .findSessionInformationBySessionInformationId(sessionInformationId);
        if (isPrivate != null)
            session.setSessionInformation(sessionInformation);

        if (isPrivate != null)
            session.setPrivate(isPrivate);
        if (capacity != null)
            session.setCapacity(capacity);
        if (description != null)
            session.setDescription(description);
        if (title != null)
            session.setTitle(title);

        return sessionRepository.save(session);
    }

     public Boolean joinSession(int sessionId, String username) {
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        var user = userRepository.findUserByUsername(username);

        if (session != null && user != null) {
            Participation sessionParticipation = new Participation();
            sessionParticipation.setRole(ParticipationRole.MEMBER);
            sessionParticipation.setIsGoing(true);
            sessionParticipation.setSession(session);
            sessionParticipation.setUserInformation(user.getUserInformation());

            return true;
        } else if (session == null) {
            throw new IncorrectDataException("Session not found", HttpStatus.BAD_REQUEST);
        } else {
            throw new IncorrectDataException("User not found", HttpStatus.BAD_REQUEST);
        }
     }
}
