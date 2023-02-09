package Study.App.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import Study.App.model.User;
import Study.App.repository.UserRepository;
import Study.App.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // USER MOCK DATA 
    private static final String user1Username = "testUsername1";
    private static final String user2Username = "testUsername2";
    private static final String user3Username = "testUsername3";

    private static final String user1Name = "testName1";
    private static final String user2Name = "testName2";
    private static final String user3Name = "testName3";

    private static final String user1Password = "testPassword1";
    private static final String user2Password = "testPassword2";
    private static final String user3Password = "testPassword3";

    @BeforeEach
    void setUpMocks(){
        User user1 = new User();        
        User user2 = new User();
        User user3 = new User();    
        
        user1.setName(UserServiceTest.user1Name);
        user2.setName(UserServiceTest.user2Name);
        user3.setName(UserServiceTest.user3Name);

        user1.setPassword(UserServiceTest.user1Password);
        user2.setPassword(UserServiceTest.user2Password);
        user3.setPassword(UserServiceTest.user3Password);

        user1.setUsername(UserServiceTest.user1Username);
        user2.setUsername(UserServiceTest.user2Username);
        user3.setUsername(UserServiceTest.user3Username);

        lenient().when(
            userRepository.findUserByUsername(anyString())
        ).thenAnswer(
            (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(user1Username)){
                    return user1; 
                } else if (invocation.getArgument(0).equals(user2Username)) {
                    return user2;
                } else{
                    return null;
                }
            }
        );
        lenient().when(
            userRepository.findUserByName(anyString())
        ).thenAnswer(
            (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(user1Name)){
                    return user1; 
                } else if (invocation.getArgument(0).equals(user2Name)) {
                    return user2;
                } else if (invocation.getArgument(0).equals(user3Name)) {
                    return user3;
                } else{
                    return null;
                }
            }
        );
        lenient().when(userRepository.save(any(User.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }

    @Test
    public void createUserTest1(){
        assertEquals(0 , userRepository.count());
        
        try{
            userService.signUpUser(user3Name, user3Username, user3Password);
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }

        User user = userRepository.findUserByName(user3Name);

        System.out.println("not null checking");        
        assertNotNull(user);
        System.out.println("not null asserted");        
        assertEquals(user3Name, user.getName());
        assertEquals(user3Username, user.getUsername());
        assertEquals(user3Password, user.getPassword());
    } 

    // @Test
    // public void createDuplicateUserNameTest1(){
    //     assertEquals(0 , userRepository.count());
    //     User user = null;

    //     try{
    //         userService.signUpUser(user1Name, user1Username, user1Password);
    //     }catch(Exception e){
    //         e.printStackTrace();
    //         fail();
    //     }

    //     user = userRepository.findUserByUsername(user1Name);

    //     assertNotNull(user);
    //     assertEquals(user1Name, user.getName());
    //     assertEquals(user1Username, user.getUsername());
    //     assertEquals(user1Password, user.getPassword());
    // } 

}
