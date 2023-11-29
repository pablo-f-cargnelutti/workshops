package edu.workana.doubles;

import edu.workana.doubles.persistence.UserRepository;
import edu.workana.doubles.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    private UserProfileService userProfileService;
    
    @Spy
    private UserRepository userRepository;
    
    @Test
    void whenGetUserProfileItReturnsValidUser() {
        // Arrange, Given
        userProfileService = new UserProfileService(userRepository);
        
        // When
        var userProfile = userProfileService.getCurrentUserProfile();
        
        // Then
        verify(userRepository, times(1)).getCurrentUserProfile();
    }
}
