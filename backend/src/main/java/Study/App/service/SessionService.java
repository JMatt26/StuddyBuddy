package Study.App.service;




import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.repository.ParticipantRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipantRepository participationRepository;
    private SessionInformationRepository sessionInformationRepository;

    public SessionService(SessionRepository sessionRepository, ParticipantRepository participationRepository, SessionInformationRepository sessionInformationRepository) {
        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
    }

    @Transactional
     public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, Set<Integer> participationIds, Integer sessionInformationId){
        Session session = new Session();

        Set<Participation> participations = new HashSet<Participation>();

        for (Integer participationId : participationIds) {
         Participation participation = participationRepository.findParticipantByParticipantId(participationId);
         if (participation != null) participations.add(participation);
        }

        SessionInformation sessionInformation = sessionInformationRepository.findSessionInformationBySessionInformationId(sessionInformationId);
        if (isPrivate != null) session.setSessionInformation(sessionInformation);

        if (isPrivate != null) session.setPrivate(isPrivate);
        if (capacity != null) session.setCapacity(capacity);
        if (description != null) session.setDescription(description);
        if (title != null) session.setTitle(title);

        return sessionRepository.save(session);
     }
}
