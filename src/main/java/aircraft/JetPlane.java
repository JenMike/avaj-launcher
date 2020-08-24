package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
import main.java.handler.Records;
import main.java.weather.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower tower;

    private final HashMap<String, String> message = new HashMap<String, String>() {
        {
            put("SUN", "Jetting away in the sunlight!");
            put("RAIN", "Jetting through the rain.");
            put("FOG", "Jetting through the fog.");
            put("SNOW", "Jetting through the snow");
            put("GROUNDED", "Jetting to the ground. No... wait...");
        }};

    public JetPlane(String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    @Override
    public void updateConditions() {
        String update = this.updateCoordinates(tower.getWeather(this.coordinates), "JetPlane");
        Records.getAllRecords().addNewData("JetPlane#" + this.name + "(" + this.id + "): " + message.get(update));
        if (update == "GROUNDED") {
            Records.getAllRecords().addNewData("JetPlane#" + this.name + "(" + this.id + "): landing.");
            Records.getAllRecords().addNewData("Current Coords ::");
            Records.getAllRecords().addNewData("Longitude :: " + this.coordinates.getLongitude());
            Records.getAllRecords().addNewData("Latitude :: " + this.coordinates.getLatitude());
            Records.getAllRecords().addNewData("height :: " + this.coordinates.getHeight());
            this.tower.unregister(this);
            Records.getAllRecords().addNewData("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        Records.getAllRecords().addNewData("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
