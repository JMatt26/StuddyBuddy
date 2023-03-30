package Study.App;

import java.text.SimpleDateFormat;
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
			Date startTime1 = SessionController.stringToDate("2021-04-01 12:00:00");
			Date endTime1 = SessionController.stringToDate("2021-04-01 13:00:00");
			session4Information.setStartTime(startTime1);
			session4Information.setEndTime(endTime1);
			List<String> tags1 = new ArrayList<String>();
			tags1.add("TESTING");
			session4Information.setTags(tags1);
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
