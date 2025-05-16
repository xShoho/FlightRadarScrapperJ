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
        return new JSONObject("{ \"flight_number\":\"" + this.flight_number +
                                "\",\"flight_time\":\"" + this.flight_time +
                                "\",\"departure_airport\":\"" + this.departure_airport +
                                "\",\"arrival_airport\":\"" + this.arrival_airport +
                                "\",\"airline\":\"" + this.airline +
                                "\",\"aircraft\":\"" + this.aircraft +
                                "\",\"flight_status\":\"" + this.status +
                                "\",\"arrival_time\":\"" + this.arrival_time + "\"}");

    }
}
