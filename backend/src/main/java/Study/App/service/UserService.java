package Study.App.service;

import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Study.App.model.User;
import Study.App.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates user using sign up.
     * 
     * @param username
     * @param password
     * @param name
     */
    @Transactional
    public User signUpUser(String name, String username, String password) {
        if (name == null || username == null || password == null) {
            throw new IllegalStateException("Name, username and password cannot be null");
        }

        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            throw new IllegalStateException("User with username " + username + " already exists");
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }
}
