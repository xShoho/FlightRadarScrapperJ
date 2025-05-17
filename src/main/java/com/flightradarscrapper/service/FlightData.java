package com.flightradarscrapper.service;

import org.json.JSONObject;

public class FlightData {
    private String flight_time;
    private String flight_number;
    private String departure_airport;
    private String arrival_airport;
    private String airline;
    private String aircraft;
    private String status;
    private String arrival_time;

    // Constructor

    public FlightData(String flight_time, String flight_number, String departure_airport, String arrival_airport, String airline, String aircraft, String status, String arrival_time) {
        this.flight_time = flight_time;
        this.flight_number = flight_number;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.airline = airline;
        this.aircraft = aircraft;
        this.status = status;
        this.arrival_time = arrival_time;
    }

    // Convert Object to JSON

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("flight_time", this.flight_time);
        json.put("flight_number", this.flight_number);
        json.put("departure_airport", this.departure_airport);
        json.put("arrival_airport", this.arrival_airport);
        json.put("airline", this.airline);
        json.put("aircraft", this.aircraft);
        json.put("status", this.status);
        json.put("arrival_time", this.arrival_time);
        return json;
    }
}
