package Study.App.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import Study.App.controller.TOs.CreateSessionTO;
import Study.App.controller.TOs.SessionInformationTO;
import Study.App.controller.TOs.SessionTO;
import Study.App.controller.SessionController;
import Study.App.model.*;

import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



import Study.App.model.enums.ParticipationRole;
import Study.App.repository.LocationRepository;
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
    private LocationRepository locationRepository;

    public SessionService(
        SessionRepository sessionRepository, 
        ParticipationRepository participationRepository, 
        SessionInformationRepository sessionInformationRepository, 
        UserRepository userRepository,
        UserInformationRepository userInformationRepository,
        LocationRepository locationRepository) {

        this.sessionRepository = sessionRepository;
        this.participationRepository = participationRepository;
        this.sessionInformationRepository = sessionInformationRepository;
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
        this.locationRepository = locationRepository;
    }

    // Get all sessions by session name
    public List<Session> getSessionsBySessionName(String title) {
        List<Session> result = sessionRepository.findAllSessionByTitle(title);
        return result;
    }

    @Transactional
    public Session createSession(Boolean isPrivate, String title, Integer capacity, String description, String username) {

        Session session = new Session();

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IncorrectDataException("User not found", HttpStatus.NOT_FOUND);
        }
        UserInformation userInformation = userInformationRepository.findUserInformationByUserUsername(user.getUsername());
        Participation sessionParticipation = new Participation();
        sessionParticipation.setRole(ParticipationRole.ADMIN);
        sessionParticipation.setIsGoing(true);
        sessionParticipation.setSession(session);
        sessionParticipation.setUserInformation(userInformation);
        participationRepository.save(sessionParticipation);

        // if (sessionInformationId != null) {
        //     SessionInformation sessionInformation = sessionInformationRepository
        //             .findSessionInformationBySessionInformationId(sessionInformationId);
        //     if (sessionInformation != null){
        //         session.setSessionInformation(sessionInformation);
        //     }
        // }

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

    /**
     * Returns the list of all sessions without any filters.
     * @return List of sessions as CreateSessionTOs
     */
    @Transactional
    public List<CreateSessionTO> getAllSessions(){
        List<Session> sessions = (List<Session>)sessionRepository.findAll();
        List<CreateSessionTO> result = new ArrayList();
        for (Session session : sessions) {
            SessionInformation sessionInformation = session.getSessionInformation();
            SessionTO sessionTO = new SessionTO(
                session.getSessionId(),
                session.isPrivate() == null ? null : session.isPrivate(),
                session.getTitle() == null ? null : session.getTitle(),
                session.getCapacity() == null ? null : session.getCapacity(),
                session.getDescription() == null ? null : session.getDescription(),
                session.getParticipations() == null ? null : session.getParticipations().size(),
                null,
                session.getSessionInformation() == null ? null : session.getSessionInformation().getSessionInformationId()
            );
            String startDate = null;
            String endDate = null;
            SessionInformationTO sessionInformationTO = null;

            if (sessionInformation != null) {
                startDate = SessionController.dateToString(sessionInformation.getStartTime());
                endDate = SessionController.dateToString(sessionInformation.getEndTime());
            
                sessionInformationTO = new SessionInformationTO(
                    sessionInformation.getSessionInformationId(),
                    startDate,
                    endDate,
                    sessionInformation.getCourses(),
                    sessionInformation.getTags(),
                    sessionInformation.isOnline(),
                    sessionInformation.getMaterialUrl(),
                    sessionInformation.getSession() == null ? null : sessionInformation.getSession().getSessionId(),
                    sessionInformation.getLocation() == null ? null : sessionInformation.getLocation().getLocationid()
                );
            }

            CreateSessionTO createSessionTO = new CreateSessionTO();
            createSessionTO.incoming = sessionTO;
            createSessionTO.incomingInfo = sessionInformationTO;
            result.add(createSessionTO);
        }  

        return result;
    }

    @Transactional
    public List<Session>  getAllActiveSessions(){
        Date date = new Date();
        List <SessionInformation> sessionInfoList = (List<SessionInformation>) sessionInformationRepository.findAll();
        List <Session> sessions = new ArrayList<Session>();
        for (SessionInformation sessionInfo : sessionInfoList){
            if (sessionInfo.getStartTime().before(date) && sessionInfo.getEndTime().after(date)){
                List<Session> temp = sessionRepository.findAllSessionBySessionInformation(sessionInfo);
                for(Session sess : temp){
                    sessions.add(sess);
                }
            }
        }
        return sessions;
    }

    @Transactional
    public List<Session> getAllUpcomingSessions(){
        Date date = new Date();
        List <SessionInformation> sessionInfoList = (List<SessionInformation>) sessionInformationRepository.findAll();
        List <Session> sessions = new ArrayList<Session>();
        for (SessionInformation sessionInfo : sessionInfoList){
            if (sessionInfo.getStartTime().after(date)){
                List<Session> temp = sessionRepository.findAllSessionBySessionInformation(sessionInfo);
                for(Session sess : temp){
                    sessions.add(sess);
                }
            }
        }
        return sessions;
    }

    public SessionInformation addInfoToSession(Integer sessionId, Date startTime, Date endTime, List<String> courses, List<String> tags, Boolean isOnline, List<String> materialUrl, Integer locationId) {
        SessionInformation sessionInformation = new SessionInformation();

        if (sessionRepository.findSessionBySessionId(sessionId) != null) {
            if (courses != null)
                sessionInformation.setCourses(courses);
            sessionInformation.setCourses(courses);
            if (startTime != null)
                sessionInformation.setStartTime(startTime);
            sessionInformation.setStartTime(startTime);
            if (endTime != null)
                sessionInformation.setEndTime(endTime);
            sessionInformation.setEndTime(endTime);
            if (isOnline != null)
                sessionInformation.setOnline(isOnline);
            if (materialUrl != null)
                sessionInformation.setMaterialUrl(materialUrl);
            sessionInformation.setMaterialUrl(materialUrl);
            if (tags != null)
                sessionInformation.setTags(tags);
            if (locationId != null)
            sessionInformation.setLocation(locationRepository.findLocationByLocationid(locationId));

            if (sessionId != null)
            sessionInformation.setSession(sessionRepository.findSessionBySessionId(sessionId));
            return sessionInformationRepository.save(sessionInformation);
        } else {
            throw new IncorrectDataException("Session not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Set<Session> getSessionsByTag(List<String> tags){
        Set<Session> sessionList = new HashSet<Session>();            
        for(String tag: tags){
            // List<String> tagList = new ArrayList<String>();
            // tagList.add(tag);
            Iterable<SessionInformation> sessionInformations = sessionInformationRepository.findAll();
            for(SessionInformation sessionInformation : sessionInformations){
                if(sessionInformation.getTags() != null && sessionInformation.getTags().contains(tag)){
                    Session session = this.sessionRepository.findSessionBySessionInformation(sessionInformation);
                    if(session != null && !sessionList.contains(session)) {
                        sessionList.add(session);
                    }
                }

            }
        }
        return sessionList;
    }
}
