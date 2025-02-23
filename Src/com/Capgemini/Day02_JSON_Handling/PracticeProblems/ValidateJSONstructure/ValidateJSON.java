
package com.Capgemini.Day02_JSON_Handling.PracticeProblems.ValidateJSONstructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.File;

public class ValidateJSON{
    // Method for checking the basic structure of JSON
    public static boolean validateJSONStructure(JsonNode jsonNode) {
        // Check if the expected fields exist and their types are correct
        if (!jsonNode.has("name") || !jsonNode.get("name").isTextual()) {
            System.out.println("Missing or invalid 'name' field");
            return false;
        }

        if (!jsonNode.has("email") || !jsonNode.get("email").isTextual()) {
            System.out.println("Missing or invalid 'email' field");
            return false;
        }

        if (!jsonNode.has("age") || !jsonNode.get("age").isInt()) {
            System.out.println("Missing or invalid 'age' field");
            return false;
        }

        // Add more fields or checks as needed
        return true;
    }
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode schemaNode = objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/PracticeProblems/ValidateJSONstructure/Validate.json"));
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            JsonNode jsonData = objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/PracticeProblems/ValidateJSONstructure/ValidData.json"));
if(validateJSONStructure(jsonData)){
    System.out.println("Json is valid!");
}
else{
    System.out.println("Json is not valid.");
}

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
