package com.arctiq.wright.model;

import java.text.ParseException;
import java.util.Date;

public class Flight {
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureTime;
    private Date arrivalTime;
    private FlightStatus status;
    private int fuelRange;
    private boolean fuelTankLeak;
    private String flightLogSignature;
    private FlightLog flightLog;
    private String aerobaticSequenceSignature;

    public Flight(int id, String flightNumber, String origin, String destination, Date departureTime, Date arrivalTime, FlightStatus status, int fuelRange, boolean fuelTankLeak, String flightLogSignature, String aerobaticSequenceSignature) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.fuelRange = fuelRange;
        this.fuelTankLeak = fuelTankLeak;
        this.flightLogSignature = flightLogSignature;
        this.aerobaticSequenceSignature = aerobaticSequenceSignature;
        try {
            this.flightLog = FlightLog.parse(flightLogSignature);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // getters
    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public int getFuelRange() {
        return fuelRange;
    }

    public boolean isFuelTankLeak() {
        return fuelTankLeak;
    }

    public String getFlightLogSignature() {
        return flightLogSignature;
    }

    public String getAerobaticSequenceSignature() {
        return aerobaticSequenceSignature;
    }

    public FlightLog getFlightLog() {
        return flightLog;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public void setFuelRange(int fuelRange) {
        this.fuelRange = fuelRange;
    }

    public void setFuelTankLeak(boolean fuelTankLeak) {
        this.fuelTankLeak = fuelTankLeak;
    }

    public void setFlightLogSignature(String flightLogSignature) {
        this.flightLogSignature = flightLogSignature;
    }

    public void setAerobaticSequenceSignature(String aerobaticSequenceSignature) {
        this.aerobaticSequenceSignature = aerobaticSequenceSignature;
    }
}