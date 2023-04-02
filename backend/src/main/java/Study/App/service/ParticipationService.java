package Study.App.service;

import org.springframework.stereotype.Service;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.UserRepository;
import Study.App.repository.SessionRepository;
import jakarta.transaction.Transactional;

@Service
public class ParticipationService {
    private SessionRepository sessionRepository;
    private ParticipationRepository participantRepository;
    private UserRepository userRepository;

    public ParticipationService(SessionRepository sessionRepository, ParticipationRepository participantRepository,
            UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
    }

    public Participation deleteParticipant(int participantId, int sessionId) {
        Session aSession = sessionRepository.findSessionBySessionId(sessionId);
        Participation aParticipant = participantRepository.findParticipationByParticipationID(participantId);

        if (aSession!=null && aParticipant!=null) {
            if (aSession.getParticipations().contains(aParticipant)) {
                aSession.getParticipations().remove(aParticipant);
                participantRepository.delete(aParticipant);
            }
        }
        return aParticipant;
    }

}