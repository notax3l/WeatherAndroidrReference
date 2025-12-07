package com.example.assignment2_tanishqpratapsingh.model;

// This class basically works as a data holder for the weather info.
// I am using it to store all the values I get back from the Weather API.

public class WeatherModel {

    // Name of the city we are fetching weather for
    public String cityName;

    // Weather condition like Cloudy, Rain, Sunny etc.
    public String condition;

    // Temperature in Celsius and Fahrenheit
    public double tempC;
    public double tempF;

    // What the temperature feels like (Celsius)
    public double feelsLikeC;

    // Humidity percentage
    public int humidity;

    // Wind speed in km/h
    public double windKph;

    // Constructor to quickly create the model with all values
    public WeatherModel(String cityName, String condition, double tempC, double tempF,
                        double feelsLikeC, int humidity, double windKph) {

        // Assigning all the values to the class so I can use them later
        this.cityName = cityName;
        this.condition = condition;
        this.tempC = tempC;
        this.tempF = tempF;
        this.feelsLikeC = feelsLikeC;
        this.humidity = humidity;
        this.windKph = windKph;
    }
}
