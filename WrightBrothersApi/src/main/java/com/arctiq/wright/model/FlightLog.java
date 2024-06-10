package com.arctiq.wright.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightLog {
    private Date date;
    private String departure;
    private String arrival;
    private String flightNumber;

    public FlightLog(Date date, String departure, String arrival, String flightNumber) {
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
        this.flightNumber = flightNumber;
    }

    public static FlightLog parse(String flightLogSignature) throws ParseException {
        String[] parts = flightLogSignature.split("-");
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = formatter.parse(parts[0]);
        String departure = parts[1];
        String arrival = parts[2];
        String flightNumber = parts[3];

        return new FlightLog(date, departure, arrival, flightNumber);
    }

    // getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
