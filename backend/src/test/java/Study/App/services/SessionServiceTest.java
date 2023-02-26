package Study.App.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
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
    
    @BeforeEach
    void setUpMocks(){
        SessionInformation sessionInfo1 = new SessionInformation();
        List<String> sessionInfo1Tags = new ArrayList<String>();
        sessionInfo1Tags.add("Chess");
        sessionInfo1.setTag(sessionInfo1Tags);

        SessionInformation sessionInfo2 = new SessionInformation();
        List<String> sessionInfo2Tags = new ArrayList<String>();
        sessionInfo2Tags.add("Movies");
        sessionInfo2.setTag(sessionInfo1Tags);

        when(sessionInformationRepository.findAllSessionInformationByTagsIn(anyList()))
        .thenReturn(List.of(sessionInfo1, sessionInfo2));
    }
    
    @Test
    public void testGetAllSessionsByTag() {
        List<String> testTags = new ArrayList<String>();
        testTags.add("Chess");
        assertEquals(1, sessionService.getAllSessionsByTag(testTags).size());
    }
}
