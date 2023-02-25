package Study.App.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Study.App.model.SecurityUser;
import Study.App.model.User;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public AppUserDetailsService(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot find user with username: " + username);
        }
        return new SecurityUser(user);
    }

}
