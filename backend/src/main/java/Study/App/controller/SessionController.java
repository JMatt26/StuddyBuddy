package Study.App.controller;

import Study.App.controller.TOs.UserTO;
import Study.App.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Study.App.controller.TOs.SessionTO;
import Study.App.model.Session;
import Study.App.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/session")

public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sess1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getS1(Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        return "Hell";
    }
    
    @GetMapping("/sess2")
    @PreAuthorize("hasAuthority('MEMBER')")
    public String getS2(Authentication authentication){
        System.out.println(authentication.getAuthorities().toString());
        return "Hell";
    }

    @PostMapping("/createSession")
    public ResponseEntity<SessionTO> createTheSession(@RequestBody SessionTO incoming) {
        
        Session aNewSession = sessionService.createSession(incoming.isPrivate, incoming.title, incoming.capacity, incoming.description, incoming.participationIds, incoming.sessionInformationId);

        if (aNewSession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SessionTO>(new SessionTO(aNewSession.getSessionId(), aNewSession.isPrivate(), aNewSession.getTitle(), aNewSession.getCapacity(), aNewSession.getDescription(), null, null, aNewSession.getSessionInformation().getSessionInformationId()), HttpStatus.OK);
    }

    @PutMapping("/joinSession")
    public void joinSession(@RequestParam Integer sessionId, @RequestParam Integer userId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @GetMapping("/displayUsersInSession")
    public List<UserTO> displayUsersInSession(@RequestParam Integer sessionId){

    }

    private UserTO convertToDTO(User u){
        if (u == null){
            throw new IllegalArgumentException("There is no such User");
        }
        UserTO userTO = new UserTO(u.getName(),u.getUsername(), null);
        return userTO;
    }
}