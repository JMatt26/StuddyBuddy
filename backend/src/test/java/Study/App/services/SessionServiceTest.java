import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import Study.App.model.SessionInformation;
import Study.App.model.Session;
import Study.App.repository.SessionInformationRepository;
import Study.App.service.SessionService;

@SpringBootTest
public class SessionServiceTest {
    
    @Mock
    private SessionInformationRepository sessionInformationRepository;
    
    @InjectMocks
    private SessionService sessionService;
    
    @Test
    public void testGetAllSessionsByTag() {
        // Create two SessionInformation objects, one with the "Chess" tag and the other without
        SessionInformation sessionInfo1 = new SessionInformation();
        SessionInformation sessionInfo2 = new SessionInformation();
        
        // Mock the repository method to return the two SessionInformation objects when called with any tag
        when(sessionInformationRepository.findAllSessionInformationByTagsContaining(anyString()))
                .thenReturn(Streamable.of(sessionInfo1, sessionInfo2));
        
        // Call the method with the "Chess" tag and verify that the returned object contains the "Chess" tag
        List<Session> sessions = sessionService.getAllSessionsByTag(List.of("Chess"));
        List<String> tags = new ArrayList<>();
        sessions.forEach(session -> tags.addAll(session.getSessionInformation().getTags()));
        Assertions.assertTrue(tags.contains("Chess"));
    }
}
