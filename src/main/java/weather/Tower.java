package main.java.weather;

import java.util.ArrayList;
import java.util.List;

import main.java.aircraft.interfaces.Flyable;

public class Tower {

    private final List<Flyable> observers = new ArrayList<>();

    public void changeWeather() {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }

    public void register(Flyable flyable) {

        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }
}
