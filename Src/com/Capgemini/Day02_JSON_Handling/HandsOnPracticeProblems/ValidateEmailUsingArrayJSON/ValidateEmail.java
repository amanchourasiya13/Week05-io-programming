package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.ValidateEmailUsingArrayJSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.File;

public class ValidateEmail {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ValidateEmailUsingArrayJSON/Email.json"));

            File schemaFile=new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ValidateEmailUsingArrayJSON/Email.json");
           JsonNode schemaNode=objectMapper.readTree(schemaFile);
            JsonSchemaFactory factory=JsonSchemaFactory.byDefault();
            JsonSchema schema=factory.getJsonSchema(schemaNode);


            if(schema.validate(jsonData).isSuccess()){
                System.out.println("JSON Valid-Email is valid!");
            }else{
                System.out.println("Invalid JSON-Email is not valid!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
