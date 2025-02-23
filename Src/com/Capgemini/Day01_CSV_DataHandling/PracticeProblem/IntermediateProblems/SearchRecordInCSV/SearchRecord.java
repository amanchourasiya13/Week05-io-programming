package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.IntermediateProblems.SearchRecordInCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
public class SearchRecord {
    public static void main(String[] args) {
        String filePath="src/main/java/Day01_CSV_DataHandling/PracticeProblems/IntermediateProblems/SearchRecordInCSV/Employees.csv";
        String name="aman";
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if(nextLine[1].equalsIgnoreCase(name))
                    System.out.println("Department: "+nextLine[2]+",Salary: "+nextLine[3]);
            }
        }catch(CsvValidationException | IOException e){
            e.printStackTrace();
        }
    }
}

