package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
import main.java.handler.AppException;
import main.java.handler.Records;

public class AircraftFactory
{
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws AppException {

        if ((longitude < 0) || (latitude < 0) || (height < 0)) {
            Records.getAllRecords().addNewData("Error :: negative coordinates given");
            throw new AppException("Error :: negative coordinates given");
        }

        if (height > 100) {
            height = 100;
        }
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(name, coordinates);
            case "jetplane":
                return new JetPlane(name, coordinates);
            case "balloon":
            case "baloon":
                return new Baloon(name, coordinates);
        }

        return null;
    }
}
