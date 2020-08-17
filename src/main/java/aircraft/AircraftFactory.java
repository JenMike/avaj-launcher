package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;

public class AircraftFactory
{
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        return switch (type) {
            case "Balloon" -> new Balloon(name, coordinates);
            case "Helicopter" -> new Helicopter(name, coordinates);
            case "JetPlane" -> new JetPlane(name, coordinates);
            default -> null;
        };
    }
}
