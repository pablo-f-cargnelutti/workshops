package edu.workana.doubles.entity;

public class Reward {
    private String subscription;
    private double points;
    public Reward(String subscription, double rewardPoints) {
        this.subscription = subscription;
        this.points = rewardPoints;
    }

    public double getPoints() {
        return points;
    }
}
