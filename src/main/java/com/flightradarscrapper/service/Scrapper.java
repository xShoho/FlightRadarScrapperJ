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
    private List<String> arrival_urls = new ArrayList<>();
    private List<String> departure_urls = new ArrayList<>();
    private final WebDriver driver = new FirefoxDriver();

    public List<FlightData> scrapper(boolean is_arrival) {
        if(is_arrival) {
            for(String url: arrival_urls) {
                this.scrape(url);
            }
        } else {
            for(String url: departure_urls) {
                this.scrape(url);
            }
        }
        return this.flights;
    }

    private void scrape(String url) {
        // Establish Connection
        this.driver.get(url);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
    }

    public void getUrls() {
        // Establish Connection
        this.driver.get("https://www.flightradar24.com/data/airports/poland");
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

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
