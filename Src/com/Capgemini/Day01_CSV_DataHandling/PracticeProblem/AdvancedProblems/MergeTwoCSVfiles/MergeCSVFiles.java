package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.MergeTwoCSVfiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;

public class MergeCSVFiles {
    public static void main(String[] args) {
        // Paths to the input CSV files
        String file1Path = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/MergeTwoCSVfiles/input.csv";  // Contains ID, Name, Age
        String file2Path = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/MergeTwoCSVfiles/input2.csv";  // Contains ID, Marks, Grade
        // Path to the output merged CSV file
        String outputPath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/MergeTwoCSVfiles/output.csv";

        // Read both CSV files and merge them
        Map<String, String[]> studentsData1 = readCSV(file1Path);
        Map<String, String[]> studentsData2 = readCSV(file2Path);

        // Write the merged data to the output CSV
        if (mergeAndWriteCSV(studentsData1, studentsData2, outputPath)) {
            System.out.println("Data Merged Successfully.");
        }
    }
    // Method to read a CSV file into a Map, using ID as the key
    public static Map<String, String[]> readCSV(String filePath) {
        Map<String, String[]> data = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String id = line[0];  // ID is in the first column
                data.put(id, line);  // Store the entire row, keyed by ID
            }
        } catch (CsvValidationException| IOException e) {
             e.printStackTrace();
        }
        return data;
    }

    // Method to merge the two CSVs based on ID and write to a new file
    public static boolean mergeAndWriteCSV(Map<String, String[]> data1, Map<String, String[]> data2, String outputPath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {
            // Write the header for the merged CSV file
           writer.writeNext(new String[] {"ID", "Name", "Age", "Marks", "Grade"});

            // Iterate over the first dataset and merge with the second dataset based on ID
            for (Map.Entry<String, String[]> entry : data1.entrySet()) {
                String id = entry.getKey();
                String[] dataFromFile1 = entry.getValue();

                // Check if the ID exists in the second dataset
                if (data2.containsKey(id)) {
                    String[] dataFromFile2 = data2.get(id);
                    // Merge the data from both files based on ID and write to the output file
                    String[] mergedData = new String[dataFromFile1.length + dataFromFile2.length - 1];
                    System.arraycopy(dataFromFile1, 0, mergedData, 0, dataFromFile1.length);
                    System.arraycopy(dataFromFile2, 1, mergedData, dataFromFile1.length, dataFromFile2.length - 1);
                   writer.writeNext(mergedData);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
