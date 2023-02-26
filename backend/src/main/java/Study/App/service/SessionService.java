package Study.App.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Study.App.model.*;

import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import Study.App.model.enums.ParticipationRole;
import Study.App.repository.ParticipationRepository;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.repository.UserInformationRepository;
import Study.App.repository.UserRepository;
import Study.exceptions.IncorrectDataException;
import jakarta.transaction.Transactional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private ParticipationRepository participationRepository;
    private SessionInformationRepository sessionInformationRepository;
    private UserRepository userRepository;
    private UserInformationRepository userInformationRepository;

    public SessionService(
        SessionRepository sessionRepository, 
        ParticipationRepository participationRepository, 
        SessionInformationRepository sessionInformationRepository, 
        UserRepository userRepository,
        UserInformationRepository userInformationRepository) {

        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
    }

    // Get all sessions by session name
    public List<Session> getSessionsBySessionName(String title) {
        return sessionRepository.findAllSessionByTitle(title);
    }

    @Transactional
    public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, String username, Integer sessionInformationId) {

        Session session = new Session();

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IncorrectDataException("User not found", HttpStatus.BAD_REQUEST);
        }
        UserInformation userInformation = userInformationRepository.findUserInformationByUserUsername(user.getUsername());
        Participation sessionParticipation = new Participation();
        sessionParticipation.setRole(ParticipationRole.ADMIN);
        sessionParticipation.setIsGoing(true);
        sessionParticipation.setSession(session);
        sessionParticipation.setUserInformation(userInformation);
        participationRepository.save(sessionParticipation);

        if (sessionInformationId != null) {
            SessionInformation sessionInformation = sessionInformationRepository
                    .findSessionInformationBySessionInformationId(sessionInformationId);
            if (sessionInformation != null){
                session.setSessionInformation(sessionInformation);
            }
        }

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

     public Boolean joinSession(Integer sessionId, String username) {
        Session session = this.sessionRepository.findSessionBySessionId(sessionId);
        User user = this.userRepository.findUserByUsername(username);
        UserInformation userInformation = this.userInformationRepository.findUserInformationByUserUsername(user.getUsername());
        Participation existingParticipation = this.participationRepository.findAllParticipationBySessionSessionId(sessionId).stream().filter(p -> p.getUserInformation().getUser().getUsername().equals(username)).findFirst().orElse(null);
        if (session != null && user != null && userInformation != null && existingParticipation == null) {
            existingParticipation = new Participation();
            existingParticipation.setRole(ParticipationRole.MEMBER);
            existingParticipation.setIsGoing(true);
            existingParticipation.setSession(session);
            existingParticipation.setUserInformation(userInformation);
            participationRepository.save(existingParticipation);
            return true;
        } else if (existingParticipation != null) {
            throw new IncorrectDataException("You are already in this session", HttpStatus.BAD_REQUEST);
        } else {
            throw new IncorrectDataException("Session, user, or User information was not found", HttpStatus.BAD_REQUEST);
        }
     }

    public Boolean deleteSession(int sessionId, String username) {
        Session session = sessionRepository.findSessionBySessionId(sessionId);

        Integer userID = userRepository.findUserByUsername(username).getUserId();
        List<Participation> participations = participationRepository.findAllParticipationBySessionSessionId(sessionId);
        Integer adminId = participations.stream().filter(p -> p.getRole() == ParticipationRole.ADMIN).findFirst().get().getUserInformation().getUser().getUserId();

        if (userID != adminId) {
            throw new IncorrectDataException("You are not admin of this session", HttpStatus.BAD_REQUEST);
        }
        if (session != null) {
            sessionRepository.delete(session);
            return true;
        } else {
            throw new IncorrectDataException("Session not found", HttpStatus.BAD_REQUEST);
        }
    }
    
    @Transactional
    public List<User> getAllUsersInSession(Integer sessionId){
        List<Participation> participationList =  participationRepository.findAllParticipationBySessionSessionId(sessionId);
        List<User> userList = new ArrayList<User>();
        for(Participation participation: participationList) {
            UserInformation userInformation = participation.getUserInformation();
            User user = null;
            if (userInformation != null) {
                user = userInformation.getUser();
            };
            if(user != null && participation.isGoing()){
                userList.add(user);
            }
        }

        return userList;
    }

    @Transactional
    public List<Session> getAllSessionsByTag(List<String> tags){
        List<Session> sessionList = new ArrayList<Session>();            
        for(String tag: tags){
            List<String> tagList = new ArrayList<String>();
            tagList.add(tag);
            List<SessionInformation> sessionInformations = sessionInformationRepository.findAllSessionInformationByTagsIn(tagList);
            sessionInformations.stream().forEach(sessionInformation -> {
                Session session = this.sessionRepository.findAllSessionBySessionInformation(sessionInformation).get(0);
                if(session != null && !sessionList.contains(session)) {
                    sessionList.add(session);
                }
            });
        }
        return sessionList;
    }
}
