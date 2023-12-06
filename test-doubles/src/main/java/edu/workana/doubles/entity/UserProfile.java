package edu.workana.doubles.entity;

public class UserProfile {
    private String subscription;
    private double points;
    public UserProfile(String subscription, double points) {
        this.subscription = subscription;
        this.points = points;
    }

    public String getSubscription() {
        return subscription;
    }


    public double getCurrentPoints() {
        return points;
    }
}
