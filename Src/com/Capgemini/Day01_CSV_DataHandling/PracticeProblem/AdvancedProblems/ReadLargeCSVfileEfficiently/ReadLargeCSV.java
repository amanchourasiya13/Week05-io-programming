package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.ReadLargeCSVfileEfficiently;

import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ReadLargeCSV {
    public static void main(String[] args) {
        // Path to the large CSV file
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ReadLargeCSVfileEfficiently/largeData500mb.csv";

        // Read the large CSV file efficiently in chunks of 100 lines
        processCSVInChunks(filePath, 100);
    }

    // Method to process CSV in chunks of 'chunkSize' lines
    public static void processCSVInChunks(String filePath, int chunkSize) {
        BufferedReader reader = null;
        int recordCount = 0;
        int totalProcessed = 0;
        List<String[]> chunk = new ArrayList<>();

        try {
            // Create a BufferedReader to read the file
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Split the line into columns (assuming CSV columns are separated by commas)
                String[] record = line.split(",");
                chunk.add(record);
                recordCount++;

                // When we've processed 100 records, display and reset
                if (recordCount == chunkSize) {
                    System.out.println("Processed " + chunkSize + " records.");
                    totalProcessed += chunkSize;
                    // Optionally, you can process 'chunk' here (e.g., save it to a database, etc.)
                    chunk.clear();  // Clear the chunk after processing
                    recordCount = 0;  // Reset record count for the next chunk
                }
            }

            // If there are remaining records in the last chunk
            if (recordCount > 0) {
                System.out.println("Processed " + recordCount + " records.");
                totalProcessed += recordCount;
            }

            System.out.println("Total records processed: " + totalProcessed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

