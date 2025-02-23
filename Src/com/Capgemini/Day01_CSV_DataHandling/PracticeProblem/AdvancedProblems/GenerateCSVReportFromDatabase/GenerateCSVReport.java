package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.GenerateCSVReportFromDatabase;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.FileWriter;
import java.sql.*;

public class GenerateCSVReport {
    public static void main(String[] args) {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/localInstanceMySql80"; // Replace with your DB URL
        String dbUser = "aman";  //  your DB username
        String dbPassword = "YES";  // your DB password

        // Path to the output CSV file
        String csvFilePath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/GenerateCSVReportFromDatabase/outputData.csv";

        // Fetch employee data and write to CSV
        fetchAndWriteToCSV(jdbcURL, dbUser, dbPassword, csvFilePath);
    }

    // Method to fetch data from the database and write to CSV
    public static void fetchAndWriteToCSV(String jdbcURL, String dbUser, String dbPassword, String csvFilePath) {
        // SQL query to fetch employee data
        String sqlQuery = "SELECT employee_id, name, department, salary FROM employees"; //  your table structure

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery);
             FileWriter writer = new FileWriter(csvFilePath);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Write the header to the CSV file
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            csvWriter.writeNext(header);

            // Fetch data from ResultSet and write to CSV
            while (rs.next()) {
                String employeeId = rs.getString("employee_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                // Write each employee's data as a new row in the CSV
                String[] employeeData = {employeeId, name, department, String.valueOf(salary)};
                csvWriter.writeNext(employeeData);
            }

            System.out.println("Employee report generated successfully!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
