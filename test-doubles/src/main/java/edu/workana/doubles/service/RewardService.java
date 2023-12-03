package edu.workana.doubles.service;

import edu.workana.doubles.entity.Reward;

public class RewardService {

    private final UserProfileService userProfileService;

    public RewardService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    
    public Reward getReward() {
        var userProfile = userProfileService.getCurrentUserProfile();
        var subscription = userProfile.getSubscription();
        var points = userProfile.getCurrentPoints();
        
        double rewardPoints = 0;
        if (subscription.equalsIgnoreCase("basic")) {
            rewardPoints = points * 0.7;
        } else if (subscription.equalsIgnoreCase("premium")) {
            rewardPoints = points * 2;
        } else {
            throw new InvalidSubscription(subscription);
        }
        
        return new Reward(subscription, rewardPoints);
    }
}
