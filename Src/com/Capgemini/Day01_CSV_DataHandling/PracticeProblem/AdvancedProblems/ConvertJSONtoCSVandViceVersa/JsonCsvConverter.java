package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.ConvertJSONtoCSVandViceVersa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class JsonCsvConverter {

    public static void main(String[] args) {
        // JSON to CSV conversion
        String jsonFilePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ConvertJSONtoCSVandViceVersa/student.json";  // Input JSON file path
        String csvFilePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ConvertJSONtoCSVandViceVersa/output.csv";    // Output CSV file path

        // CSV to JSON conversion
        String inputCsvPath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ConvertJSONtoCSVandViceVersa/student.csv";   // Input CSV file path
        String outputJsonPath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/ConvertJSONtoCSVandViceVersa/output.json";  // Output JSON file path

        // Convert JSON to CSV
        jsonToCsv(jsonFilePath, csvFilePath);

        // Convert CSV to JSON
        csvToJson(inputCsvPath, outputJsonPath);
    }

    // Convert JSON to CSV
    public static void jsonToCsv(String jsonFilePath, String csvFilePath) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into a List of Maps
            List<Map<String, Object>> students = Arrays.asList(objectMapper.readValue(new File(jsonFilePath), Map[].class));

            // Create a CSVWriter object
            FileWriter fileWriter = new FileWriter(csvFilePath);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Get headers from the keys of the first student map
            if (students.isEmpty()) {
                System.out.println("No data in JSON file");
                return;
            }
            Set<String> headersSet = students.get(0).keySet();
            String[] headers = headersSet.toArray(new String[0]);

            // Write the header to the CSV
            csvWriter.writeNext(headers);

            // Write each student's data as a row in the CSV
            for (Map<String, Object> student : students) {
                String[] studentData = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    studentData[i] = String.valueOf(student.get(headers[i]));
                }
                csvWriter.writeNext(studentData);
            }

            // Close the writer
            csvWriter.close();
            System.out.println("JSON to CSV conversion completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convert CSV to JSON
//// Convert CSV to JSON
//    public static void csvToJson(String csvFilePath, String jsonFilePath) {
//        try {
//            // Create CSVReader object
//            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
//            List<String[]> rows =  csvReader.readAll();
//            // Get the header (first row)
//            String[] header = rows.get(0);
//
//            // Create a list to store student records (as Maps)
//            List<Map<String, Object>> students = new ArrayList<>();
//
//            // Iterate through each row and convert to a Map (representing a student record)
//            for (int i = 1; i < rows.size(); i++) {  // Start from 1 to skip the header
//                String[] row = rows.get(i);
//                Map<String, Object> student = new HashMap<>();
//                for (int j = 0; j < header.length; j++) {
//                    student.put(header[j], row[j]);
//                }
//                students.add(student);
//            }
//
//            // Create ObjectMapper for JSON conversion
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Write the list of students to the JSON file
//            objectMapper.writeValue(new File(jsonFilePath), students);
//
//            // Close the CSVReader
//            csvReader.close();
//            System.out.println("CSV to JSON conversion completed successfully.");
//
//        } catch (CsvValidationException|IOException e) {
//            e.printStackTrace();
//        }


/*
   public static void csvToJson(String input,String output){
       JSONArray jsonArray=new JSONArray();
       try(BufferedReader br=new BufferedReader(new FileReader(input)){
           String line;
           String headers[]=null;
           while((line=br.readLine())!=null){
               String[]values=line.split(",");

               if(headers==null){
                   headers=values;
                   continue;
               }
               JSONObject jsonObject=new JSONObject();

               for(int i=0;i<values.length;i++){
                   jsonObject.put(headers[i],values[i]);
               }
               jsonArray.put(jsonObject);
           }
           String jsonString =jsonArray.toString(4);
           System.out.println("Converted JSON: ");
           System.out.println(jsonString);
       }catch (IOException e){
           e.printStackTrace();
       }
   }

    */



        public static void csvToJson(String csvFilePath, String jsonFilePath) {
            try {
                // Create CSVReader object
                CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));

                // Read the header (first row)
                String[] header = csvReader.readNext(); // Read the first row as header
                if (header == null) {
                    System.out.println("CSV file is empty or improperly formatted.");
                    return;
                }
                System.out.println("CSV Header: " + String.join(", ", header));

                // Create a list to store student records (as Maps)
                List<Map<String, Object>> students = new ArrayList<>();

                // Iterate through each row and convert to a Map (representing a student record)
                String[] row;
                int rowCount = 0;
                while ((row = csvReader.readNext()) != null) {  // Read each subsequent row
                    if (row.length != header.length) {
                        System.out.println("Skipping row with incorrect number of columns: " + String.join(", ", row));
                        continue; // Skip rows with incorrect column count
                    }
                    Map<String, Object> student = new HashMap<>();
                    for (int j = 0; j < header.length; j++) {
                        student.put(header[j], row[j]);
                    }
                    students.add(student);
                    rowCount++;
                }

                if (rowCount == 0) {
                    System.out.println("No data found in the CSV file.");
                    return;
                }

                // Create ObjectMapper for JSON conversion
                ObjectMapper objectMapper = new ObjectMapper();

                // Write the list of students to the JSON file
                objectMapper.writeValue(new File(jsonFilePath), students);

                // Close the CSVReader
                csvReader.close();
                System.out.println("CSV to JSON conversion completed successfully.");

            } catch (CsvValidationException|IOException e) {
                System.out.println("Error during CSV to JSON conversion: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

