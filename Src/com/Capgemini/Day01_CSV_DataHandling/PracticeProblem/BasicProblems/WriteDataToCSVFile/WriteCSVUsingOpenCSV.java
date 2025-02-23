package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.BasicProblems.WriteDataToCSVFile;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
public class WriteCSVUsingOpenCSV {
    public static void main(String[] args) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/java/Day01_CSV_DataHandling/PracticeProblems/BasicProblems/WriteDataToCSVFile/Output.csv"))) {
            String[] header = {"ID", "Name", "Department", "Salary"};
            String[] emp1 = {"101", "Aman", "Finance", "62000"};
            String[] emp2 = {"102", "Johnson", "Sales", "58000"};
            String[] emp3 = {"103", "Johny", "Accountant", "60000"};
            String[] emp4 = {"104",  "Rohan", "IT", "58000"};
            String[] emp5 = {"105", "Mohan", "Manager", "64000"};
            writer.writeNext(header);
            writer.writeNext(emp1);
            writer.writeNext(emp2);
            writer.writeNext(emp3);
            writer.writeNext(emp4);
            writer.writeNext(emp5);
            System.out.println("CSV file written successfully using OpenCSV!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
