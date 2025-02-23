package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.BasicProblems.ReadCSVFileAndPrintData;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import java.io.FileReader;
import java.io.IOException;
public class CsvData {
    public static void main(String[] args) {
        try (CSVReader reader = new CSVReader(new FileReader<>("src/main/java/Day01_CSV_DataHandling/PracticeProblems/BasicProblems/ReadCSVFileAndPrintData/Data.csv"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println("ID: " + nextLine[0] + ", Name: " + nextLine[1]+",Age: "+nextLine[2]+",Marks: "+nextLine[3]);
            }
        }catch(CsvValidationException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

