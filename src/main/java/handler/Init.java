package main.java.handler;

import main.java.aircraft.AircraftFactory;
import main.java.weather.WeatherProvider;
import main.java.weather.WeatherTower;

import java.io.*;
import java.util.Objects;

public class Init {

    public static int cycles;
   // public static AircraftFactory af = new AircraftFactory();
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
                            Records.getAllRecords().addNewData("Error :: File contains a negative integer");
                            return;
                        }
                    } catch (NumberFormatException nfe) {
                        throw new AppException(" Line 1 in file must contain only an integer");
                    }
                else {
                    lines = line.split(" ");

                    if (lines.length == 1 && lines[0].isEmpty())
                        continue;

                    if (lines.length != 5)
                        throw new AppException("Error :: line " + line + ": must contain 5 parameters.");

                    try {
                        Objects.requireNonNull(AircraftFactory.newAircraft(
                                lines[0],
                                lines[1],
                                Integer.parseInt(lines[2]),
                                Integer.parseInt(lines[3]),
                                Integer.parseInt(lines[4])
                        )).registerTower(wt); //NullPointerException fix (requireNonNull)
                    } catch (Exception e) {
                        throw new AppException("Error: line " + line + ": parameter indices 3, 4 and 5 must be Integers");
                    }
                }
                lineIndex++;
            }

            br.close();
        } catch (Exception e) {
            Records.getAllRecords().addNewData("Error :: " + e.getMessage());
            throw new AppException(e);
        }
       // Records.getAllRecords().writeDataToFile();
    }
}
