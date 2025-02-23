package com.Capgemini.Day02_JSON_Handling.PracticeProblems.ConvertJavaObjectIntoJSON;

import org.json.JSONObject;

public class ConvertJavaToJSON {
   static class Car{
        String model;
        String color;
        String brand;
        double price;
     public Car(String model,String color,String brand,double price){
         this.color=color;
         this.model=model;
         this.brand=brand;
         this.price=price;
     }
     public JSONObject toJSON(){
         JSONObject jsonObject=new JSONObject();
         jsonObject.put("Model",this.model);
         jsonObject.put("Color",this.color);
         jsonObject.put("Brand",this.brand);
         jsonObject.put("Price",this.price);
                 return jsonObject;
       }
    }
    public static void main(String[] args) {
        Car car=new Car("Tesla Model 5","Black","Tesla",79999.99);
        JSONObject carJson=car.toJSON();
        System.out.println(carJson.toString());
    }
}
