package main.java.aircraft;

import java.util.HashMap;

public class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    private long nextId()
    {
        return ++(Aircraft.idCounter);
    }

    protected Aircraft(String name, Coordinates coordinates)
    {
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    protected String updateCoordinates(String weather, String type) {
        int newHeight = 0;
        int[] changes = null;

        switch (weather) {
            case "SUN":
                changes = sun.get(type);
                newHeight = Math.min(this.coordinates.getHeight() + changes[coords.HEIGHT.ordinal()], 100);
                break;
            case "RAIN":
                changes = rain.get(type);
                newHeight = this.coordinates.getHeight() + changes[coords.HEIGHT.ordinal()];
                break;
            case "FOG":
                changes = fog.get(type);
                newHeight = this.coordinates.getHeight() + changes[coords.HEIGHT.ordinal()];
                break;
            case "SNOW":
                changes = snow.get(type);
                newHeight = this.coordinates.getHeight() + changes[coords.HEIGHT.ordinal()];
                break;
        }
        assert changes != null;
        this.coordinates = new Coordinates(
                this.coordinates.getLongitude() + changes[coords.LONGITUDE.ordinal()],
                this.coordinates.getLatitude() + changes[coords.LATITUDE.ordinal()],
                newHeight);
        if (newHeight <= 0) {
            return "GROUNDED";
        }
        return weather;
    }

    private enum coords {
        LONGITUDE, LATITUDE, HEIGHT
    }

    private final HashMap<String, int[]> rain = new HashMap<String, int[]>() {
        {
            put("JetPlane", new int[] {0, 5, 0});
            put("Helicopter", new int[] {5, 0, 0});
            put("Baloon", new int[] {0, 0, -5});
        }};

    private final HashMap<String, int[]> sun = new HashMap<String, int[]>() {
        {
            put("JetPlane", new int[] {0, 10, 2});
            put("Helicopter", new int[] {10, 0, 2});
            put("Baloon", new int[] {2, 0, 4});
        }};

    private final HashMap<String, int[]> fog = new HashMap<String, int[]>() {
        {
            put("JetPlane", new int[] {0, 1, 0});
            put("Helicopter", new int[] {1, 0, 0});
            put("Baloon", new int[] {0, 0, -3});
        }};

    private final HashMap<String, int[]> snow = new HashMap<String, int[]>() {
        {
            put("JetPlane", new int[] {0, 0, -7});
            put("Helicopter", new int[] {0, 0, -12});
            put("Baloon", new int[] {0, 0, -15});
        }};
}