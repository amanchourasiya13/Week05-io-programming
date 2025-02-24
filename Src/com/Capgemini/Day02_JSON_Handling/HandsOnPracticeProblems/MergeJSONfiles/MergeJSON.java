package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.MergeJSONfiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MergeJSON {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode1 = objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/MergeJSONfiles/Merged1.json"));
         JsonNode jsonNode2=objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/MergeJSONfiles/Merged2.json"));
         JsonNode  mergedJson=jsonNode1.deepCopy();
            ((ObjectNode)mergedJson).setAll((ObjectNode)jsonNode2 );
            objectMapper.writeValue(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/MergeJSONfiles/MergedFile.json"),mergedJson);
            System.out.println("Merged JSON saved to mergedFile.json");
        } catch (IOException e) {
       e.printStackTrace();
        }
        }
}
