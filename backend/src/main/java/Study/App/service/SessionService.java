package Study.App.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipationRepository participationRepository;
    private SessionInformationRepository sessionInformationRepository;

    public SessionService(SessionRepository sessionRepository, ParticipationRepository participationRepository,
            SessionInformationRepository sessionInformationRepository) {
        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
    }

    @Transactional
    public Session createSession(Boolean isPrivate, String title, Integer capacity, String description,
            Set<Integer> participationIds, Integer sessionInformationId) {
        Session session = new Session();

        Set<Participation> participations = new HashSet<Participation>();

        for (Integer participationId : participationIds) {
            Participation participation = participationRepository.findParticipationByParticipationID(participationId);
            if (participation != null)
                participations.add(participation);
        }

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

    public Boolean isSessionFull(Session session) {
        
        // List<Participation> participations = participationRepository.findAllParticipationsBySession(session);
        // int numAttendees = 0;
        // for (Participation participation : participations) {
        //     if (participation.isGoing()) {
        //         numAttendees++;
        //     }
        // }
        int numAttendees = getNumAttendees(session);
        if (numAttendees >= session.getCapacity()) {
            return true;
        }
        else {
            return false;
        }
     }

     public Integer getNumAttendees(Session session) {
        List<Participation> participations = participationRepository.findAllParticipationsBySession(session);
        int numAttendees = 0;
        for (Participation participation : participations) {
            if (participation.isGoing()) {
                numAttendees++;
            }
        }

        return numAttendees;
     }
}
