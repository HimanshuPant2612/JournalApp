package com.himanshu.journalApp.service;

import com.himanshu.journalApp.entity.JournalApplication;
import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.entity.repository.UserRepository;
import com.himanshu.journalApp.entity.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JournalApplication.class)
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
  //To disable any test to run we can use @Disabled annotation
@Disabled
@Test
    public void testByUserName()
{

    User user = userRepository.findByUsername("Ram");
    assertTrue(!user.getJournalEntries().isEmpty());
}

//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Ram",
//            "shyam",
//            "HanumanJi"
//    })
//    public void testFindByUsername(String name)
//    {
//        Users user=  userRepository.findByUsername(name);
//        assertNotNull(user.getJournalEntries().isEmpty());
//    }

    @Autowired
    private UserService userService;
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
     assertTrue(userService.saveNewUser(user));
    }

////
    //ParameterizedTest --> To avoid hardcode, it's suitable to use Parameterized Test

//    @CsvSource({
//            "1,2,3",
//            "2,3,0"
//    })
//    @ParameterizedTest
//    public void test(int a, int b, int expected)
//    {
//        assertEquals(expected, a+b);
//    }

}
