package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.ConvertCSVdataIntoObjects;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class CSVtoObjects {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ConvertCSVdataIntoObjects/Student.csv";

        // Read the CSV and convert data into a list of rows
        List<String[]> students = readCSV(filePath);

        // Print the list of student records
        for (String[] student : students) {
            // Print each row: assuming the columns are Name, Age, Email
            System.out.println("Name: " + student[0] + ", Age: " + student[1] + ", Email: " + student[2]);
        }
    }

    // Method to read and parse the CSV file using OpenCSV
    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Add each row (array of strings) to the list
                records.add(line);
            }
        } catch (CsvValidationException|IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
