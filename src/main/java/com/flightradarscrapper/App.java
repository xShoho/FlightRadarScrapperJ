package com.flightradarscrapper;

import com.flightradarscrapper.service.FlightData;
import com.flightradarscrapper.service.Scrapper;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        JSONArray json_array = new JSONArray();
        Scrapper scrapper = new Scrapper();

        scrapper.getUrls();

        List<FlightData> flights = scrapper.scrapper(true);
        flights.addAll(scrapper.scrapper(false));

        for(FlightData flight: flights) {
            json_array.put(flight.toJSON());
        }

        try (FileWriter file = new FileWriter("flights.json")) {
            file.write(json_array.toString(2));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
