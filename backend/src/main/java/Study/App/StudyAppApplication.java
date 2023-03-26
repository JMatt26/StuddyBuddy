package Study.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Study.App.model.*;
import Study.App.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import Study.App.model.enums.ParticipationRole;

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

			SessionInformation sessionInformation1 = new SessionInformation();
			Date startTime = new Date(12345678);
			Date endTime = new Date(22345678);
			Location location1 = new Location();
			location1.setPostalCode("H3T 1J4");
			location1.setCity("Montreal");
			location1.setProvince("Quebec");
			location1.setStreetAddress("1450 Rue Guy");
			location1.setBuildingName("Trottier");
			location1.setRoomNumber("A-100");
			locationRepository.save(location1);

			sessionInformation1.setLocation(location1);
			List<String> tags = new ArrayList<String>();
			tags.add("ECSE");
			tags.add("COMP");
			tags.add("Chess");
			sessionInformation1.setTags(tags);
			sessionInformation1.setCourses(courses);
			sessionInformation1.setStartTime(startTime);
			sessionInformation1.setEndTime(endTime);
			sessionInformation1.setSession(session1);
			sessionInformationRepository.save(sessionInformation1);


			Session session2 = new Session();
			session2.setTitle("ECSE 310");
			sessionRepository.save(session2);

			SessionInformation sessionInformation2 = new SessionInformation();
			Date startTime2 = new Date(12345678);
			Date endTime2 = new Date(22345678);
			Location location2 = new Location();
			location2.setPostalCode("H3T 1J4");
			location2.setCity("Montreal");
			location2.setProvince("Quebec");
			location2.setStreetAddress("11771 Frigon");
			location2.setBuildingName("Dante House");
			location2.setRoomNumber("A-100");
			locationRepository.save(location2);

			sessionInformation2.setLocation(location2);
			List<String> tags2 = new ArrayList<String>();
			tags2.add("ECSE");
			tags2.add("COMP");
			tags2.add("Chess");
			sessionInformation2.setTags(tags2);
			sessionInformation2.setCourses(courses);
			sessionInformation2.setStartTime(startTime2);
			sessionInformation2.setEndTime(endTime2);
			sessionInformation2.setSession(session2);
			sessionInformationRepository.save(sessionInformation2);


//
//			Session session3 = new Session();
//			session3.setTitle("COMP 251");
//			sessionRepository.save(session3);
			
		};
	}

}
