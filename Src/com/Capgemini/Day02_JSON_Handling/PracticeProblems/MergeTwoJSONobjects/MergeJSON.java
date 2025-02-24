package com.Capgemini.Day02_JSON_Handling.PracticeProblems.MergeTwoJSONobjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class MergeJSON {
    public static void main(String[] args) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","aman");

        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("age",21);

        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("jsonObject1",jsonObject1);
        jsonObject2.put("jsonObject",jsonObject);

        System.out.println(jsonObject2.toString());
    }
}
