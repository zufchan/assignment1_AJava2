package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException  {

        FileWriter csvWriter = new FileWriter("report.csv");
        csvWriter.append("Name");
        csvWriter.append(",");
        csvWriter.append("ID");
        csvWriter.append(",");
        csvWriter.append("Published_from");
        csvWriter.append(",");
        csvWriter.append("Published_to");
        csvWriter.append(",");
        csvWriter.append("Avg_content_length");
        csvWriter.append("\n");

        csvWriter.flush();
        csvWriter.close();

        final File folder = new File("./assignment1_simplified_input_csv");
        long threads_count = getFolderSize(folder);
        TaskManager factory = new TaskManager((int) threads_count);
        for (int i = 0; i < threads_count; i++) {
            factory.addTask(new CSV_Annalyzer("./assignment1_simplified_input_csv/part"+(i+1)+".csv"));
        }



        factory.runTasks();


    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int files_count = files.length;
        return files_count;
    }
}
