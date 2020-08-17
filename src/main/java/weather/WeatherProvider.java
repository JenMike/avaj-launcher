package main.java.weather;

import main.java.aircraft.Coordinates;

public class WeatherProvider {
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {

        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        int value = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();

        return weather[value % 4];
    }
}
