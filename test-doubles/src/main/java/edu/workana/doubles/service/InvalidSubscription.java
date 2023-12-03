package edu.workana.doubles.service;

public class InvalidSubscription extends RuntimeException {
    InvalidSubscription(String invalid) {
        super(invalid);
    }
}
