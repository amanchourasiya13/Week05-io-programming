package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.IntermediateProblems.ModifyCSVFile;

import java.io.*;
import java.util.*;

public class UpdateSalary {

    public static void main(String[] args) {
        String inputFilePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/IntermediateProblems/SearchRecordInCSV/Employees.csv"; // Input CSV file path
        String outputFilePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/ModifyCSVFile/updateEmployee.csv"; // Output CSV file path

        List<String[]> updatedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            boolean isFirstLine = true;

            // Read and process each line from the CSV file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (isFirstLine) {
                    updatedData.add(data); // Add the header row to the updated data list
                    isFirstLine = false;
                    continue;
                }

                // Check if the department is IT and update salary
                String department = data[2]; //  the department is in the 3rd column (index 2)
                double salary = Double.parseDouble(data[3]); // salary is in the 4th column (index 3)

                if ("IT".equalsIgnoreCase(department)) {
                    salary *= 1.10; // Increase salary by 10%
                }

                data[3] = String.format("%.2f", salary); // Update the salary in the record
                updatedData.add(data);
            }

            // Write the updated records to a new CSV file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                for (String[] row : updatedData) {
                    writer.write(String.join(",", row));
                    writer.newLine();
                }
            }

            System.out.println("File updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

