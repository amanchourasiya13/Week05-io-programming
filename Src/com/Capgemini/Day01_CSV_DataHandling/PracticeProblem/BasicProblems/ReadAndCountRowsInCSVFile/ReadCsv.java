package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.BasicProblems.ReadAndCountRowsInCSVFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReadCsv {
    public static void main(String[] args) {
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/BasicProblems/ReadCSVFileAndPrintData/Data.csv";
        int count=-1;// excluding header row.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                System.out.println("ID: " + columns[0] + ", Name: " + columns[1]+",Age: "+columns[2]+",Marks: "+columns[3]);
                count++;
            }
            System.out.println("Total columns: "+count);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

