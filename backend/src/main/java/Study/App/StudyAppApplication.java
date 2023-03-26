package Study.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import Study.App.controller.SessionController;
import Study.App.model.Location;
import Study.App.model.Session;
import Study.App.model.SessionInformation;
import Study.App.model.User;
import Study.App.model.UserInformation;
import Study.App.model.enums.ParticipationRole;
import Study.App.repository.LocationRepository;
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
			LocationRepository locationRepository,
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

			// Session that has all attributes
			Session session4 = new Session();
			session4.setTitle("Full Trial");
			session4.setCapacity(1);
			session4.setDescription("This is a full trial session for the rendering and flow of data");
			session4.setPrivate(false);

			//Session Information
			SessionInformation session4Information = new SessionInformation();
			Date startTime = SessionController.stringToDate("2021-04-01 12:00:00");
			Date endTime = SessionController.stringToDate("2021-04-01 13:00:00");
			session4Information.setStartTime(startTime);
			session4Information.setEndTime(endTime);
			List<String> tags = new ArrayList<String>();
			tags.add("TESTING");
			session4Information.setTags(tags);
			sessionInformationRepository.save(session4Information);
			session4.setSessionInformation(session4Information);

			// Setting Location
			Location location = new Location();
			location.setBuildingName("Trottier");
			location.setRoomNumber("A-100");
			location.setCity("Montreal");
			location.setProvince("Quebec");
			location.setStreetAddress("1450 Rue Guy");
			location.setPostalCode("H3H 1M8");
			locationRepository.save(location);
			session4.getSessionInformation().setLocation(location);
			sessionInformationRepository.save(session4Information);

			sessionRepository.save(session4);
		};
	}

}
