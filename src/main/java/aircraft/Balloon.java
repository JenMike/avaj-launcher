package main.java.aircraft;

import main.java.aircraft.interfaces.Flyable;
import main.java.weather.WeatherTower;

import java.util.HashMap;

public class Balloon extends Aircraft implements Flyable {
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

    public Balloon(String name, Coordinates coordinates) {
        super (name, coordinates);
    }

    @Override
    public void updateConditions() {
        String update = this.updateCoordinates(tower.getWeather(this.coordinates), "Balloon");
        System.out.println("Balloon#" + this.name + "(" + this.id + "): " + message.get(update));
        if (update.equals("GROUNDED")) {
            System.out.println("Balloon#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
