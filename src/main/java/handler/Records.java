package main.java.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Records {

    private static final Records records = new Records();
    private ArrayList<String> flightData = new ArrayList<>();

    private Records(){}

    public static Records getAllRecords() {
        return records;
    }

    public void addNewData(String newData) {
        System.out.println(newData);
        flightData.add(newData);
    }

    public void writeDataToFile() throws AppException {
        try {
            if (flightData.size() == 0)
                return;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("simulation.txt"));
            for (String s : flightData) {
                bufferedWriter.write(s);
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (Exception e)
        {
            System.out.println("Error writing to file");
            throw new AppException(e);
        }
    }
}
