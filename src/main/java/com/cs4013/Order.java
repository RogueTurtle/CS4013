package com.cs4013;

import java.util.ArrayList;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class Order {

    //Can only be Created, Waiting, Cancelled
    private String status;
    private double price;
    private ArrayList<Food> foodsOrdered = new ArrayList<>();
    //private double moneyRequired; //May be needed not sure yet
    private Menu menu;

    public Order(Menu menu) {
        this.menu = menu;
    }

    public Order(Menu menu, ArrayList<Food> foodsOrdered) {
        this.menu = menu;
        this.foodsOrdered = foodsOrdered;
    }

    public void addFood(String foodName) {
        for(Food food : menu.getAllMeals()) {
            if(food.getName().equals(foodName)) {
                foodsOrdered.add(food);
            }
        }
    }

    public double getPrice() {
    //TODO complete method to return total order price
        return 0;
    }
    //you were changing only changes the local status that you created input into the method which is seperate from the datafield
    //Trying to make changing statuses flexible
    public void changeStatus(String newStatus) {
        newStatus = status.toUpperCase(); 
        if(newStatus.contains("CREATE") || newStatus.contains("MADE") || newStatus.contains("ORDERED")) {
            this.status = "CREATED";
        }
        else if(newStatus.contains("WAIT") || newStatus.contains("MADE") || newStatus.contains("TRANSIT")) {
            this.status = "WAITING";
        }
        else if(newStatus.contains("CANCEL") || newStatus.contains("STOP") || newStatus.contains("ABORT")) {
            this.status = "CANCELLED";
        }
        else {
            throw new RuntimeException("Error, status not valid");
        }
    }

}
