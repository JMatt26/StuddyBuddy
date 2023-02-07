package Study.App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Study.App.controller.TOs.SessionTORequest;
import Study.App.controller.TOs.SessionTOResponse;
import Study.App.model.Session;
import Study.App.service.SessionService;

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
    public ResponseEntity<SessionTOResponse> createTheSession(@RequestBody SessionTORequest incoming) {
        
        Session aNewSession = sessionService.createSession(incoming.isPrivate, incoming.title, incoming.capacity, incoming.description, incoming.attendees, incoming.participantId, incoming.sessionInformationId);

        if (aNewSession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SessionTOResponse>(new SessionTOResponse(aNewSession.getSessionId(), aNewSession.isPrivate(), aNewSession.getTitle(), aNewSession.getCapacity(), aNewSession.getDescription(), aNewSession.getAttendees(), aNewSession.getParticipant().getParticipantId(), aNewSession.getSessionInformation().getSessionInformationId()), HttpStatus.OK);
    }

}