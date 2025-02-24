package com.Capgemini.Day02_JSON_Handling.PracticeProblems.ReadJSONFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ReadJSON {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/Day02_JSON_Handling/PracticeProblems/ReadJSONFile/data.json";
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            for(JsonNode node:jsonNode ){
                String name=node.get("name").asText();
                String email=node.get("email").asText();
                System.out.println("Name: "+name+", Email: "+email);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
