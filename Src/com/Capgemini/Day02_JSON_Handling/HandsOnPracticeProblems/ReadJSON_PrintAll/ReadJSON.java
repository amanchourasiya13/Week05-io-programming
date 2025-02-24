package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.ReadJSON_PrintAll;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public class ReadJSON {
        public static void main(String[] args) {
            try{
                ObjectMapper objectMapper=new ObjectMapper();
                JsonNode jsonNode=objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/PracticeProblems/ParseJSON_FilterRecords/AgeData.json"));

//                for(JsonNode node: jsonNode) {
//                    int age = node.get("age").asInt();
//                    String name = node.get("name").asText();
//                    String email = node.get("email").asText();
//                    System.out.println("Name: " + name + ",Age: " + age + ",Email: " + email);
//                }

              printKeysAndValues(jsonNode);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        public static void printKeysAndValues(JsonNode node){
            if(node.isObject()){
                Iterator<Map.Entry<String,JsonNode>>fields= node.fields();
                while(fields.hasNext()){
                    Map.Entry<String,JsonNode>field=fields.next();
                    String key=field.getKey();
                    JsonNode value=field.getValue();

                    System.out.println("Key: "+key+",Value: "+value);
                    printKeysAndValues(value);
                }
            } else if (node.isArray()) {
                for(JsonNode item: node){
                    printKeysAndValues(item);
                }
            }
        }
    }
