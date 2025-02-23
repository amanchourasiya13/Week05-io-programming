package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.DetectDuplicatesInCSVfile;


import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class DetectDuplicatesInCSV {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/DetectDuplicatesInCSVfile/mixData.csv";

        // Detect duplicates in the CSV file
        detectDuplicates(filePath);
    }

    // Method to detect and print duplicate records based on ID
    public static void detectDuplicates(String filePath) {

        Set<String> seenIDs = new HashSet<>();  // To track IDs we've encountered
        List<String[]> duplicates = new ArrayList<>();  // To store duplicate records

        try (BufferedReader reader=new BufferedReader(new FileReader(filePath))){
            // Create a BufferedReader to read the file
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Split the line into columns (assuming CSV columns are separated by commas)
                String[] record = line.split(",");

                // The ID is assumed to be in the first column (index 0)
                String id = record[0];

                // Check if the ID is already in the seen IDs set
                if (seenIDs.contains(id)) {
                    // If it's a duplicate, add it to the duplicates list
                    duplicates.add(record);
                } else {
                    // Otherwise, add the ID to the seen set
                    seenIDs.add(id);
                }
            }

            // Print out all duplicate records
            if (duplicates.isEmpty()) {
                System.out.println("No duplicates found.");
            } else {
                System.out.println("Duplicate records based on ID:");
                for (String[] duplicate : duplicates) {
                    System.out.println(Arrays.toString(duplicate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
