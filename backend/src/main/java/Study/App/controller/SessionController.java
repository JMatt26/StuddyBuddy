package Study.App.controller;

import Study.App.controller.TOs.UserTO;
import Study.App.model.User;
import Study.App.repository.SessionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Study.App.controller.TOs.CreateSessionTO;
import Study.App.controller.TOs.SessionInformationTO;
import Study.App.controller.TOs.SessionTO;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.service.SessionService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private SessionService sessionService;
    private SessionRepository sessionRepository;

    public SessionController(SessionService sessionService, SessionRepository sessionRepository) {
        this.sessionService = sessionService;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/sess1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getS1(Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        return "Hell";
    }

    @GetMapping("/sess2")
    @PreAuthorize("hasAuthority('MEMBER')")
    public String getS2(Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        return "Hell";
    }

    @PostMapping("/createSession")
    public ResponseEntity<SessionTO> createTheSession(@RequestBody CreateSessionTO createSessionTO) {

        try {
            var username = SecurityContextHolder.getContext().getAuthentication().getName();

            SessionTO incoming = createSessionTO.incoming;
            SessionInformationTO incomingInfo = createSessionTO.incomingInfo;

            Session aNewSession = sessionService.createSession(
            incoming.isPrivate,
            incoming.title,
            incoming.capacity,
            incoming.description,
            username);
            var sessionId = aNewSession.getSessionId();

            SessionInformation newSessionInfo = sessionService.addInfoToSession(
                    sessionId,
                    null,
                    null,
                    incomingInfo.course,
                    incomingInfo.isOnline,
                    incomingInfo.materialUrl,
                    incomingInfo.locationId);
    
            if (newSessionInfo == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                aNewSession.setSessionInformation(newSessionInfo);
                sessionRepository.save(aNewSession);
            }
    
            System.out.println("Session created: " + aNewSession.toString());
            System.out.println("Session info created: " + newSessionInfo.toString());

            return new ResponseEntity<SessionTO>(
                    new SessionTO(
                            aNewSession.getSessionId(),
                            aNewSession.isPrivate(),
                            aNewSession.getTitle(),
                            aNewSession.getCapacity(),
                            aNewSession.getDescription(),
                            null,
                            null,
                            aNewSession.getSessionInformation() == null ? null
                                    : aNewSession.getSessionInformation().getSessionInformationId()),
                    HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<SessionTO>(new SessionTO(
                null,
                null,
                e.getMessage(),
                null,
                null,
                null,
                null,
                null
            ), HttpStatus.I_AM_A_TEAPOT);
        }

        // var startTime = Date.valueOf(incomingInfo.startTime);
        // var endTime = Date.valueOf(incomingInfo.endTime);
        
    }

    @PostMapping("/joinSession")
    public ResponseEntity<String> joinSession(@RequestParam Integer sessionId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        var joinStatus = sessionService.joinSession(sessionId, username);

        if (joinStatus == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (joinStatus == false) {
            return new ResponseEntity<String>("Failed to join session", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteSession")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteSession(@RequestParam Integer sessionId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        var deleteStatus = sessionService.deleteSession(sessionId, username);

        if (deleteStatus == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (deleteStatus == false) {
            return new ResponseEntity<String>("Failed to delete session", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("Session Deleted", HttpStatus.OK);
        }

    }

    @GetMapping("/getAllUsersInSession")
    public ResponseEntity<List<UserTO>> getAllUsersInSession(@RequestParam Integer sessionId) {
        List<User> userList = sessionService.getAllUsersInSession(sessionId);
        List<UserTO> userTOList = new ArrayList<>();
        if (userList != null) {
            for (User user : userList) {
                UserTO userTO = convertToDTO(user);
                userTOList.add(userTO);
            }
            return new ResponseEntity<List<UserTO>>(userTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<UserTO>>(HttpStatus.BAD_REQUEST);
        }

    }

    // GET all sessions by session name
    @GetMapping("/getAllSessionsBySessionName")
    public ResponseEntity<List<SessionTO>> getAllSessionsBySessionName(@RequestParam String sessionName) {
        List<Session> sessionList = sessionService.getSessionsBySessionName(sessionName);
        List<SessionTO> sessionTOList = new ArrayList<>();
        if (sessionList != null) {
            sessionList.stream().forEach(session -> {
                sessionTOList.add(new SessionTO(
                        session.getSessionId(),
                        session.isPrivate(),
                        session.getTitle(),
                        session.getCapacity(),
                        session.getDescription(),
                        null,
                        null,
                        session.getSessionInformation() == null ? null
                                : session.getSessionInformation().getSessionInformationId()));
            });
            return new ResponseEntity<List<SessionTO>>(sessionTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<SessionTO>>(HttpStatus.BAD_REQUEST);
        }
    }

    private UserTO convertToDTO(User u) {
        if (u == null) {
            throw new IllegalArgumentException("There is no such User");
        }
        UserTO userTO = new UserTO(null, u.getName(), u.getUsername(), null);
        return userTO;
    }
}