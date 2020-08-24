package main.java.simulator;

import main.java.aircraft.AircraftFactory;
import main.java.aircraft.interfaces.Flyable;
import main.java.handler.AppException;
import main.java.handler.Init;
import main.java.handler.Records;
import main.java.weather.WeatherTower;
import main.java.weather.Tower;

import java.io.*;
import java.util.ConcurrentModificationException;

public class Simulator extends Init {

    public static void main(String[] args) throws AppException {
        if (args.length < 1) {
            return;
        }

        String scenarioFile = args[0];

        try {
            startSimulation(new File(scenarioFile));
        } catch (AppException e) {
            e.getMessage();
            return;
        }

        while (cycles-- > 0) {
            wt.changeWeather();
        }

    }
}