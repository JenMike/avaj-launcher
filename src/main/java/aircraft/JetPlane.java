package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
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
        System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + message.get(update));
        if (update == "GROUNDED") {
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
