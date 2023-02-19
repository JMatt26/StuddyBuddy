package Study.App.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Study.App.model.User;

import org.springframework.stereotype.Service;

import Study.App.model.Participation;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipationRepository participationRepository;
    private SessionInformationRepository sessionInformationRepository;
    private UserRepository userRepository;


    public SessionService(SessionRepository sessionRepository, ParticipationRepository participationRepository,
                          SessionInformationRepository sessionInformationRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
        this.userRepository = userRepository;

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
    @Transactional
    public List<User> displayUsersInSession(Integer sessionId) {
        Session searchedSession = sessionRepository.findSessionBySessionId(sessionId);

        Set<Participation> participationList = searchedSession.getParticipants();
        List<User> userList = new ArrayList<User>();
        for (Participation participation : participationList) {

            String participantUsername = participation.getUsername();
            User user = userRepository.findUserByUsername(participantUsername);
            if (user != null) {
                userList.add(user);
            }



        }
        return userList;
    }

//    private <T> List<T> toList(Iterable<T> iterable){
//        List<T> resultList = new ArrayList<T>();
//        for(T t : iterable){
//            resultList.add(t);
//        }
//        return resultList;
//    }
}
