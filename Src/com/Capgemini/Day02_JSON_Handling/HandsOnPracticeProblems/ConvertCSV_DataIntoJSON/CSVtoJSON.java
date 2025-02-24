package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.ConvertCSV_DataIntoJSON;

import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class CSVtoJSON{
    public static void main(String[] args) {
        JSONArray jsonArray=new JSONArray();
        try(BufferedReader br=new BufferedReader(new FileReader("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ConvertCSV_DataIntoJSON/Data.csv "))){
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

            FileWriter fr=new FileWriter(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ConvertCSV_DataIntoJSON/output.json"));
        fr.write(jsonString);
            System.out.println("JSON data has been written to output.json");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}