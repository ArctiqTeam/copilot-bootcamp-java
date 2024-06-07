package com.arctiq.wright.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
        super();
    }

    public FlightNotFoundException(String message) {
        super(message);
    }
}
