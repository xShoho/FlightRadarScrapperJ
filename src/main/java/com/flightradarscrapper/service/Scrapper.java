package com.flightradarscrapper.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Scrapper {
    private final List<FlightData> flights = new ArrayList<>();
    private List<String> urls = new ArrayList<>();
    private final WebDriver driver = new FirefoxDriver();

    public Scrapper(List<String> urls) {
        this.urls = urls;
    }

    public void scrape() {
        for(String url : urls) {
            // Establish Connection
            driver.get(url);

        }
    }
}
