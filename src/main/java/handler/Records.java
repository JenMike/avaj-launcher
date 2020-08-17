package main.java.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Records {

    private static final Records records = new Records();
    private ArrayList<String> data = new ArrayList<>();

    private Records(){}

    public static Records getAllRecords() {
        return records;
    }

    public void addNewData(String newData) {
        data.add(newData);
    }

    public void writeDataToFile() throws AppException {
        try {
            if (data.size() == 0)
                return;
            System.console().printf("Writing data to file...\n\n\n");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("simulation.txt"));
            for (String s : data) {
                bufferedWriter.write(s);
                System.console().printf(s + "\n");
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (Exception e)
        {
            throw new AppException(e);
        }
    }
}
