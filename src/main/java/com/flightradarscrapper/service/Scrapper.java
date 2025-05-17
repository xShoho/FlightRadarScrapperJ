package com.flightradarscrapper.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    private final List<FlightData> flights = new ArrayList<>();
    private final List<String> arrival_urls = new ArrayList<>();
    private final List<String> departure_urls = new ArrayList<>();
    private final WebDriver driver = new FirefoxDriver();

    public List<FlightData> scrapper(boolean is_arrival) {
        if(is_arrival) {
            for(String url: arrival_urls) {
                this.flights.add(this.scrape(url, is_arrival));
            }
        } else {
            for(String url: departure_urls) {
                this.flights.add(this.scrape(url, is_arrival));
            }
        }

        driver.close();
        return this.flights;
    }

    private FlightData scrape(String url, boolean is_arrival) {
        // Initialize variables to create Flight Data object
        String flight_time = new String();
        String flight_number = new String();
        String main_airport = new String();
        String secondary_airport = new String();
        String airline = new String();
        String aircraft = new String();
        String status = new String();
        String arrival_time = new String();

        // Establish Connection
        this.driver.get(url);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

        // Getting main airport name
        List<WebElement> headers = driver.findElements(By.tagName("header"));

        if (!headers.isEmpty()) {
            main_airport = headers.get(0).findElement(By.tagName("h2")).getText();
        }

        // Getting list of registered flights
        List<WebElement> tbody = driver.findElements(By.tagName("tbody"));

        if(!tbody.isEmpty()) {
            List<WebElement> rows_with_date = tbody.get(0).findElements(By.xpath("//tr[@data-date]"));

            if(!rows_with_date.isEmpty()) {
                for(WebElement row: rows_with_date) {
                    List<WebElement> table_columns = row.findElements(By.tagName("td"));

                    if(!table_columns.isEmpty()) {
                        // Parse flight time
                        flight_time = table_columns.get(0).getText();

                        // Parse flight number
                        flight_number = table_columns.get(1).findElement(By.tagName("a")).getText();

                        // Parse secondary airport
                        secondary_airport = table_columns.get(2).findElement(By.tagName("div")).findElement(By.tagName("a")).getText().replaceAll("[()]", "");

                        // Parse airline
                        airline = table_columns.get(3).findElement(By.tagName("a")).getText();

                        // Parse aircraft model
                        aircraft = table_columns.get(4).findElement(By.tagName("span")).getText();

                        // Parse flight status and arrival time
                        arrival_time = table_columns.get(6).getText();
                        status = table_columns.get(6).findElement(By.tagName("span")).getText();
                    }
                }
            }
        }

        if(is_arrival) { return new FlightData(flight_time, flight_number, secondary_airport, main_airport, airline, aircraft, status, arrival_time); }
        else { return new FlightData(flight_time, flight_number, main_airport, secondary_airport, airline, aircraft, status, arrival_time); }
    }

    public void getUrls() {
        // Establish Connection
        this.driver.get("https://www.flightradar24.com/data/airports/poland");
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

        // Getting urls
        List<WebElement> tbody = driver.findElements(By.tagName("tbody"));

        if(!tbody.isEmpty()) {
            List<WebElement> links = tbody.get(0).findElements(By.tagName("a"));

            if(!links.isEmpty()) {
                for(WebElement link: links) {
                    String href = link.getAttribute("href");

                    if(!href.startsWith("#") && href != null) {
                        this.arrival_urls.add(href + "/arrivals");
                        this.departure_urls.add(href + "/departures");
                    }
                }
            }
        }
    }
}