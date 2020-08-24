package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
import main.java.handler.Records;
import main.java.weather.WeatherTower;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower tower;

    private final HashMap<String, String> message = new HashMap<String, String>() {
        {
            put("SUN", "THIS IS SATAN'S ARMPIT, KAREN!");
            put("RAIN", "The clouds needed to pee, Susan");
            put("FOG", "Fogget about it, Sharon");
            put("SNOW", "You snow you want it ... o_o ");
            put("GROUNDED", "Can't go to the party, I'm grounded.");
        }
    };

    public Baloon(String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    @Override
    public void updateConditions() {
        String update = this.updateCoordinates(tower.getWeather(this.coordinates), "Baloon");

        Records.getAllRecords().addNewData("Baloon#" + this.name + "(" + this.id + "): " + message.get(update));

        if (update.equals("GROUNDED")) {
            Records.getAllRecords().addNewData("Baloon#" + this.name + "(" + this.id + "): landing.");
            Records.getAllRecords().addNewData("Current Coords ::");
            Records.getAllRecords().addNewData("Longitude :: " + this.coordinates.getLongitude());
            Records.getAllRecords().addNewData("Latitude :: " + this.coordinates.getLatitude());
            Records.getAllRecords().addNewData("height :: " + this.coordinates.getHeight());
            this.tower.unregister(this);
            Records.getAllRecords().addNewData("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        Records.getAllRecords().addNewData("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
