package edu.workana.doubles.service;

import edu.workana.doubles.entity.UserProfile;
import edu.workana.doubles.persistence.UserRepository;

public class UserProfileService {
    private final UserRepository userRepository;

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserProfile getCurrentUserProfile() {
        userRepository.getCurrentUserProfile();
        return userRepository.getCurrentUserProfile();
    }
}
