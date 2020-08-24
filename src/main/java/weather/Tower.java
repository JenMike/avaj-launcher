package main.java.weather;

import java.util.ArrayList;
import java.util.List;

import main.java.aircraft.interfaces.Flyable;
import main.java.handler.AppException;
import main.java.handler.Records;

public class Tower {

    private final List<Flyable> observers = new ArrayList<>();

    public void changeWeather() throws AppException {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
            Records.getAllRecords().writeDataToFile();
        }
    }

    public void register(Flyable flyable) {

        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }
}
