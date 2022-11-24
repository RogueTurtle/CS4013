package com.cs4013;

import java.util.ArrayList;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class Order {

    //Can only be Created, Waiting, Cancelled
    private String status;
    private ArrayList<Food> foodsOrdered = new ArrayList<>();
    private double totalPrice; //May be needed not sure yet
    private Menu menu;

    /**
     * Creates an order
     * @param menu the menu the order is coming from
     */

    public Order(Menu menu) {
        this.menu = menu;
        totalPrice = 0;
        status = "CREATED";
    }

    /**
     * Adds meals to the order
     * @param meal Name of the meal to be added
     */

    public void addMeal(String meal) {
        for(Food food : menu.getAllMeals()) {
            if(food.getName().equalsIgnoreCase(meal)) {
                foodsOrdered.add(food);
                totalPrice += food.getPrice();
            }
        }
    }

    /**
     * Changes the status of the order
     * @param status new status of order
     */
    //Trying to make changing statuses flexible
    public void changeStatus(String status) {
        String newStatus = status.toUpperCase();
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

    
    /** 
     * @return double
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Order: " + foodsOrdered + "\n" +
                "Status: " + status + "\n" +
                "Total Price: " + totalPrice;
    }
}
