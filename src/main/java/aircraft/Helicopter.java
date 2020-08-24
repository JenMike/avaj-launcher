package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
import main.java.handler.Records;
import main.java.weather.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower tower;

    private final HashMap<String, String> message = new HashMap<String, String>() {
        {
            put("SUN", "Sunny days.");
            put("RAIN", "Let it rain.");
            put("FOG", "I can't see through the fog over the sound of the rotars. Welp.");
            put("SNOW", "Helipads are pretty, covered in snow");
            put("GROUNDED", "Is that the ground? Yep, feels like the ground.");
        }
    };

    public Helicopter(String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    @Override
    public void updateConditions() {
        String update = this.updateCoordinates(tower.getWeather(this.coordinates), "Helicopter");

        Records.getAllRecords().addNewData("Helicopter#" + this.name + "(" + this.id + "): " + message.get(update));

        if (update.equals("GROUNDED")) {
            Records.getAllRecords().addNewData("Helicopter#" + this.name + "(" + this.id + "): landing.");
            Records.getAllRecords().addNewData("Current Coords ::");
            Records.getAllRecords().addNewData("Longitude :: " + this.coordinates.getLongitude());
            Records.getAllRecords().addNewData("Latitude :: " + this.coordinates.getLatitude());
            Records.getAllRecords().addNewData("height :: " + this.coordinates.getHeight());
            this.tower.unregister(this);
            Records.getAllRecords().addNewData("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        Records.getAllRecords().addNewData("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
