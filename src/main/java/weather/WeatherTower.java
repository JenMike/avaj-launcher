package main.java.weather;

import main.java.aircraft.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider
                .getProvider()
                .getCurrentWeather(coordinates);
    }
}
