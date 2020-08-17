package main.java.aircraft.interfaces;

import main.java.weather.WeatherTower;

public interface Flyable {
        void	updateConditions();
        void	registerTower(WeatherTower weatherTower);
}
