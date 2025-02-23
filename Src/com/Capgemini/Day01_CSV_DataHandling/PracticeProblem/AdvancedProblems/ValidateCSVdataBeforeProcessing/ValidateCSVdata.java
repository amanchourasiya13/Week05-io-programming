package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.ValidateCSVdataBeforeProcessing;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidateCSVdata {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ValidateCSVdataBeforeProcessing/Validate.csv";

        // Read and validate the CSV data
        List<String[]> records = readCSV(filePath);

        // Process records
        for (String[] record : records) {
            String email = record[2];  // Assuming email is the third column
            String phoneNumber = record[3];  // Assuming phone number is the fourth column

            // Validate email and phone number
            if (!isValidEmail(email)) {
                System.out.println("Invalid email: " + email + " in row: " + Arrays.toString(record));
            } else if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number: " + phoneNumber + " in row: " + Arrays.toString(record));
            } else {
                // If both are valid, print the record (or process further)
                System.out.println("Valid record: " + Arrays.toString(record));
            }
        }
    }

    // Method to read and parse the CSV file using OpenCSV
    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                records.add(line);  // Add each record as a String array
            }
        } catch (CsvValidationException|IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Method to validate the email format using regex
    public static boolean isValidEmail(String email) {
        // Simple regex for validating an email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate the phone number (must be exactly 10 digits)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Ensure phone number contains exactly 10 digits (no spaces, dashes, etc.)
        String phoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
