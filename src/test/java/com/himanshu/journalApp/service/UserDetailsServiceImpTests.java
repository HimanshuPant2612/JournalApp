package com.himanshu.journalApp.service;

import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.entity.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

//@SpringBootTest(classes = JournalApplication.class)
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImpTests {
    @InjectMocks // -> is used when we r not using @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @MockBean --> is used when we are using @Autowired i.e when we r injecting bean
//    private UserRepository userRepository;
    @Mock  //--> is used when we r using @InjectMocks
    private UserRepository userRepository;
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUserNameTest()
    {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn((User) org.springframework.security.core.userdetails.User.builder().username("ram").password("sds").build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }
}
