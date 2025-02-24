package com.Capgemini.Day02_JSON_Handling.ProblemStatement.IPLandCensorAnalyzer;

import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IPLandCensorAnalyzer {

    // Method to apply censorship to team names and player names
    public static String censorTeamName(String teamName) {
        return teamName.split(" ")[0] + " ***"; // Mask the team name after the first word.
    }

    public static String censorPlayerOfMatch(String playerName) {
        return "REDACTED"; // Replace player names with "REDACTED"
    }

    // Process JSON input and apply censorship
    public static JSONArray processJsonInput(String jsonData) {
        JSONArray matches = new JSONArray(jsonData);
        for (int i = 0; i < matches.length(); i++) {
            JSONObject match = matches.getJSONObject(i);

            // Censor team names
            match.put("team1", censorTeamName(match.getString("team1")));
            match.put("team2", censorTeamName(match.getString("team2")));

            // Censor player of the match
            match.put("player_of_match", censorPlayerOfMatch(match.getString("player_of_match")));
        }
        return matches;
    }

    // Process CSV input and apply censorship with CsvException handling
    public static List<String[]> processCsvInput(String csvFilePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            // Reading all records from CSV
            records = reader.readAll();
        } catch (CsvException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }

        // Apply censorship to each row, skipping the header
        for (int i = 1; i < records.size(); i++) {  // Skip header row
            String[] record = records.get(i);

            // Censor team names
            record[1] = censorTeamName(record[1]);
            record[2] = censorTeamName(record[2]);

            // Censor player of the match
            record[6] = censorPlayerOfMatch(record[6]);
        }
        return records;
    }

    // Write the censored JSON data to file
    public static void writeJsonOutput(JSONArray censoredJson, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(censoredJson.toString(4));  // Pretty print with indentation
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
        }
    }

    // Write the censored CSV data to file
    public static void writeCsvOutput(List<String[]> censoredCsv, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(censoredCsv);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
        }
    }

    public static void main(String[] args) {
        // Sample input files
        String jsonInputFile = "src/main/java/Day02_JSON_Handling/ProblemStatement/IPLandCensorAnalyzer/input.json";
        String csvInputFile = "src/main/java/Day02_JSON_Handling/ProblemStatement/IPLandCensorAnalyzer/input.csv";

        // Output files
        String censoredJsonOutputFile = "src/main/java/Day02_JSON_Handling/ProblemStatement/IPLandCensorAnalyzer/output.json";
        String censoredCsvOutputFile = "src/main/java/Day02_JSON_Handling/ProblemStatement/IPLandCensorAnalyzer/output.csv";

        try {
            // Read and process JSON input
            String jsonData = new String(Files.readAllBytes(Paths.get(jsonInputFile)));
            JSONArray censoredJson = processJsonInput(jsonData);
            writeJsonOutput(censoredJson, censoredJsonOutputFile);

            // Read and process CSV input
            List<String[]> censoredCsv = processCsvInput(csvInputFile);
            writeCsvOutput(censoredCsv, censoredCsvOutputFile);

            System.out.println("Censorship completed and files written.");
        } catch (IOException e) {
            System.err.println("File read/write error: " + e.getMessage());
            e.printStackTrace();  // Print stack trace for debugging
        }
    }
}
