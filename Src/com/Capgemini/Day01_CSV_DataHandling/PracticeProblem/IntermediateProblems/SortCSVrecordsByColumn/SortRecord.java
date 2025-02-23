package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.IntermediateProblems.SortCSVrecordsByColumn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class SortRecord {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/IntermediateProblems/SortCSVrecordsByColumn/Salary.csv";

        List<String[]> records = readCSV(filePath);

        // Sort by salary (third column) in descending order
        records.sort((record1, record2) -> Double.compare(
                Double.parseDouble(record2[2]), Double.parseDouble(record1[2])
        ));

        // Print the top 5 highest-paid employees
        System.out.println("Top 5 Highest-Paid Employees:");
        for (int i = 0; i < Math.min(5, records.size()); i++) {
            String[] record = records.get(i);
            System.out.println(record[0] + " - " + record[1] + " - $" + record[2]);
        }
    }

    // Method to read and parse the CSV file using OpenCSV
    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                records.add(line);  // Add each record as a String array
            }
        } catch (CsvValidationException |IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
