package main.java.handler;

import main.java.aircraft.AircraftFactory;
import main.java.weather.WeatherProvider;
import main.java.weather.WeatherTower;

import java.io.*;

public class Init {

    public static int cycles;
    public static AircraftFactory af = new AircraftFactory();
    public static WeatherTower wt = new WeatherTower();

    public static void startSimulation(File scenarioFile) throws AppException {

        try {
            FileInputStream fis = new FileInputStream(scenarioFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            int lineIndex = 1;
            String[] lines;

            while ((line = br.readLine()) != null) {
                if (lineIndex == 1)
                    try {
                        cycles = Integer.parseInt(line);
                        if (cycles < 0) {
                            System.out.println("Error :: File contains a negative integer");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error :: Line 1 in file must contain only an integer");
                        throw new AppException(nfe);
                    }
                else {
                    lines = line.split(" ");

                    if (lines.length == 1 && lines[0].isEmpty())
                        continue;

                    if (lines.length != 5)
                        throw new AppException("Error :: line " + line + ": must contain 5 parameters.");

                    try {
                        af.newAircraft(
                                lines[0],
                                lines[1],
                                Integer.parseInt(lines[2]),
                                Integer.parseInt(lines[3]),
                                Integer.parseInt(lines[4])
                        ).registerTower(wt);
                    } catch (Exception e) {
                        System.out.println("Error: line " + line + ": parameter indices 3, 4 and 5 must be Integers");
                        throw new AppException(e);
                    }
                }
                lineIndex++;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error :: " + e.getMessage());
            throw new AppException(e);
        }

    }
}
