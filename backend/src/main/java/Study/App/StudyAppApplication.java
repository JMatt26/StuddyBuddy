package Study.App;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.model.User;
import Study.App.model.UserInformation;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.SessionInformationRepository;
import Study.App.repository.SessionRepository;
import Study.App.repository.UserInformationRepository;
import Study.App.repository.UserRepository;

@SpringBootApplication
public class StudyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			UserInformationRepository userInformationRepository,
			SessionInformationRepository sessionInformationRepository,
			SessionRepository sessionRepository,
			PasswordEncoder passwordEncoder) {
		return (args) -> {
			User parsa = new User();
			parsa.setUsername("parsa");
			parsa.setPassword(passwordEncoder.encode("pass"));
			parsa.getRoles().add(ParticipationRole.ADMIN);
			UserInformation parsaInformation = new UserInformation();
			userRepository.save(parsa);
			parsaInformation.setUser(parsa);
			userInformationRepository.save(parsaInformation);

			User gig = new User();
			gig.setUsername("gig");
			gig.setPassword(passwordEncoder.encode("pass"));
			gig.getRoles().add(ParticipationRole.MEMBER);
			UserInformation gigInformation = new UserInformation();
			userRepository.save(gig);
			gigInformation.setUser(gig);
			userInformationRepository.save(gigInformation);

			SessionInformation sessionInformation = new SessionInformation();
			// make a new list of strings called courses
			List<String> courses = new ArrayList<String>();
			courses.add("ECSE 428");
			sessionInformation.setCourses(courses);
			sessionInformationRepository.save(sessionInformation);

			Session session = new Session();
			sessionRepository.save(session);
			Session session1 = new Session();
			session1.setTitle("ECSE 428");
			sessionRepository.save(session1);

			Session session2 = new Session();
			session2.setTitle("ECSE 310");
			sessionRepository.save(session2);

			Session session3 = new Session();
			session3.setTitle("COMP 251");
			sessionRepository.save(session3);
			
		};
	}

}
