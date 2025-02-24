package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.ConvertJSONtoXMLformat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class ConvertJSNtoXML {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            XmlMapper xmlMapper=new XmlMapper();

            JsonNode jsonNode = objectMapper.readTree(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ConvertJSONtoXMLformat/File.json"));
            String xml=xmlMapper.writeValueAsString(jsonNode);

            System.out.println("Converted XML: ");
            System.out.println(xml);

            xmlMapper.writeValue(new File("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/ConvertJSONtoXMLformat/output.xml"),jsonNode);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
