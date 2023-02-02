package Study.App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import Study.App.service.AuthService;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = authService.generateToken(authentication);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
