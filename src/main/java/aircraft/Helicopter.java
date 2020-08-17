package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
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
        String update = this.updateCoordinates(tower.getWeather(this.coordinates), "Balloon");
        System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + message.get(update));
        if (update.equals("GROUNDED")) {
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
