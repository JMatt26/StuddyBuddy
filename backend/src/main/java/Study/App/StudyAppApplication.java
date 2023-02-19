package Study.App;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import Study.App.model.SessionInformation;
import Study.App.model.User;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.UserRepository;

@SpringBootApplication
public class StudyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			SessionInformationRepository sessionInformationRepository,
			
			PasswordEncoder passwordEncoder) {
		return (args) -> {
			User parsa = new User();
			parsa.setUsername("parsa");
			parsa.setPassword(passwordEncoder.encode("pass"));
			parsa.getRoles().add(ParticipationRole.ADMIN);

			User gig = new User();
			gig.setUsername("gig");
			gig.setPassword(passwordEncoder.encode("pass"));
			gig.getRoles().add(ParticipationRole.MEMBER);

			userRepository.save(parsa);
			userRepository.save(gig);

			SessionInformation sessionInformation = new SessionInformation();
			sessionInformation.setCourse("ECSE 428");
			sessionInformationRepository.save(sessionInformation);

		};
	}

}
