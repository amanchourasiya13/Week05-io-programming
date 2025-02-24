package com.Capgemini.Day02_JSON_Handling.PracticeProblems.ConvertListOfJavaObjectsIntoArrayJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConertObjectToArrayJSON {
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
        List<Car> cars=new ArrayList<>();
        cars.add(new Car("Tesla Model 5", "Black", "Tesla", 79999.99));
        cars.add(new Car("BMW X5", "White", "BMW", 60000.50));
        cars.add(new Car("Audi A4", "Red", "Audi", 45000.75));


        JSONArray jsonArray=new JSONArray();
        for(Car car:cars){
            jsonArray.put(car.toJSON());
        }
        System.out.println(jsonArray);
    }
    }
