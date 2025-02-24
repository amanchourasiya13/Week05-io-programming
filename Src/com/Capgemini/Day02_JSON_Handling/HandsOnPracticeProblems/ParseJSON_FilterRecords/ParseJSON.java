package com.Capgemini.Day02_JSON_Handling.PracticeProblems.ParseJSON_FilterRecords;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ParseJSON {
    public static void main(String[] args) {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode jsonNode=objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/PracticeProblems/ParseJSON_FilterRecords/AgeData.json"));

         for(JsonNode node: jsonNode){
             int age=node.get("age").asInt();
             String name=node.get("name").asText();
             String email=node.get("email").asText();
             if(age>25){
                 System.out.println("Name: "+name+",Age: "+age+",Email: "+email);
             }
         }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
