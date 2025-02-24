package com.Capgemini.Day02_JSON_Handling.HandsOnPracticeProblems.GenerateJSON_reportFromDatabaseRecords;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ReportJSONfromDatabase {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/?user=root";
        String userName="root";
        String password="Yes";

      String query="SELECT id,name,age,email FROM users";

        JSONArray jsonArray=new JSONArray();

      try(Connection conn= DriverManager.getConnection(url,userName,password);
        Statement statement=conn.createStatement();
        ResultSet resultSet=statement.executeQuery(query)){
          while(resultSet.next()){
              JSONObject jsonObject=new JSONObject();
              jsonObject.put("id", resultSet.getInt("id"));
              jsonObject.put("name", resultSet.getString("name"));
              jsonObject.put("age", resultSet.getInt("age"));
              jsonObject.put("email", resultSet.getString("email"));
jsonArray.put(jsonObject);
          }
          String jsonString =jsonArray.toString(4);
          try(FileWriter file=new FileWriter("src/main/java/Day02_JSON_Handling/HandsOnPracticeProblems/GenerateJSON_reportFromDatabaseRecords/DatabaseReport.json")){
              file.write(jsonString);
              System.out.println("JSON report has been Written to databaseReport.json");
          }
      }catch (SQLException | IOException e){
          e.printStackTrace();
      }
    }
}
