package edu.workana.doubles.service;

import edu.workana.doubles.entity.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * 1. You are not allowed to write any production code unless it is to make a failing unit test pass.
 
 * 2. You are not allowed to write any more of a unit test than is sufficient to fail; and compilation failures are failures.
 
 * 3. You are not allowed to write any more production code than is sufficient to pass the one failing unit test.
 */

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {

  private RewardService rewardService;
  
  @Mock
  private UserProfileService userProfileService;

  @BeforeEach
  void setup() {
    this.rewardService = new RewardService(userProfileService);
  }

  @Test
  void whenBasicSubscriptionReducesTheRewardPoints() {
    // Arrange, Given
    var desiredUserProfile = new UserProfile("basic", 10);
    when(userProfileService.getCurrentUserProfile()).thenReturn(desiredUserProfile);

    // Act, When
    var reward = rewardService.getReward();

    // Assert, Then
    assertThat(reward.getPoints(), is(7.0));
  }
  
  @Test
  void whenPremiumSubscriptionDoublesTheRewardPoints() {
    // Arrange, Given
    var desiredUserProfile = new UserProfile("premium", 10);
    when(userProfileService.getCurrentUserProfile()).thenReturn(desiredUserProfile);
    
    // Act, When
    var reward = rewardService.getReward();
    
    // Assert, Then
    assertThat(reward.getPoints(), is(20.0));    
  }

  @Test
  void whenInvalidSubscriptionThenShouldFail() {
    // Arrange, Given
    var desiredUserProfile = new UserProfile("fruit", 10);
    when(userProfileService.getCurrentUserProfile()).thenReturn(desiredUserProfile);

    // When, Then
    assertThrows(InvalidSubscription.class, ()-> rewardService.getReward());
  }
  
}
