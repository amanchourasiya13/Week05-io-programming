package com.Capgemini.Day02_JSON_Handling.PracticeProblems.CreateJSONobjectForStudent;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentJSON {
    public static void main(String[] args) {
        jsonObject();
    }
    public static void jsonObject(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Name","Alice");
        jsonObject.put("Age",21);

        JSONArray arr=new JSONArray();
        arr.put("Mathematics");
        arr.put("Physics");
        arr.put("Chemistry");
        arr.put("English");
        arr.put("Hindi");
        jsonObject.put("Subjects",arr);

        System.out.println(jsonObject.toString());
    }
}
