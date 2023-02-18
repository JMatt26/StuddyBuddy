package Study.App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Study.App.model.User;
import Study.App.controller.TOs.UserTO;
import Study.App.service.AuthService;
import Study.App.service.UserService;

@RestController
public class AuthController {

    private AuthService authService;
    private UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = authService.generateToken(authentication);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    // TODO: Consider if this endpoint should be here or not
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserTO incoming){
       User newUser = userService.signUpUser(incoming.name, incoming.username, incoming.password);
       
       if (newUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       } else{
            return new ResponseEntity<String>("signup successful", HttpStatus.OK);
       }
    }
}
