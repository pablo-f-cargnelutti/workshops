package edu.workana.doubles.persistence;

import edu.workana.doubles.entity.UserProfile;

public class UserRepository {
    public UserProfile getCurrentUserProfile() {
        return new UserProfile("premium", 300);
    }
}
