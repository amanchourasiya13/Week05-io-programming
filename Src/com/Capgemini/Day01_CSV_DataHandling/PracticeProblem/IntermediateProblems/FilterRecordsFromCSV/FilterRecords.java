package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.IntermediateProblems.FilterRecordsFromCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
public class FilterRecords {
    public static void main(String[] args) {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/java/Day01_CSV_DataHandling/PracticeProblems/BasicProblems/ReadCSVFileAndPrintData/Data.csv"))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                try {
                    int marks = Integer.parseInt(nextLine[3]);
                    if (marks >= 80)
                        System.out.println("ID: " + nextLine[0] + ", Name: " + nextLine[1] + ",Age: " + nextLine[2] + ",Marks: " + nextLine[3]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }catch (CsvValidationException| IOException e) {
            e.printStackTrace();
        }
    }
}

