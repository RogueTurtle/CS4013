package com.cs4013;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */

import java.util.ArrayList;

public class Menu {

    private ArrayList<Food> starters = new ArrayList<>();
    private ArrayList<Food> soups = new ArrayList<>();
    private ArrayList<Food> main_course = new ArrayList<>();
    private ArrayList<Food> dessert = new ArrayList<>();
    private ArrayList<Food> allMeals = new ArrayList<>();
    private double allPrices;

    public Menu() {
        allPrices = 0;
    }

    //This method is used to add a meal to allMeals and into one of the specified categories
    public void addMeal(String name, String type, String desc, double price) {
        Food newMeal = new Food(name, type, desc, price);

        if(type.toUpperCase().contains("STARTER")) {
            starters.add(newMeal);
        }
        else if(type.toUpperCase().contains("SOUP")) {
            soups.add(newMeal);
        }
        else if(type.toUpperCase().contains("MAIN")) {
            main_course.add(newMeal);
        }
        else if(type.toUpperCase().contains("DESSERT")) {
            dessert.add(newMeal);
        }
        else {
            //Apparently not needed so can remove since I have exception in Food already
            throw new RuntimeException("Error, the meal does not fit into any of the categories. Please specify the category");
        }
        allPrices += price;
        allMeals.add(newMeal);
    }

    //Removes meals when the name of the meal is specified
    public void removeMeal(String meal) {
        ArrayList<Food> allMealsTemp = new ArrayList<>(allMeals);
        for(Food food : allMealsTemp) {
            if(food.getName().equals(meal)) {
                starters.remove(food);
                soups.remove(food);
                main_course.remove(food);
                dessert.remove(food);
                allMeals.remove(food);
                allPrices -= food.getPrice();
            }
        }
    }

    public double getAllPrices() {
        return allPrices;
    }

    //Used to be of type Arraylist<Food>
    public String getStarters() {
        return "Starters: " + starters;
    }

    public String getSoups() {
        return "Soups: "  + soups;
    }

    public String getMainCourse() {
        return "Main Course: " + main_course;
    }

    public String getDessert() {
        return "Dessert: " + dessert;
    }

    public ArrayList<Food> getAllMeals() {
        return allMeals;
    }

    @Override
    public String toString() {
        return  "Menu: " + "\n" +
                "Starters: " + starters + "\n" +
                "Soups: " + soups +"\n" +
                "Main Course: " + main_course +"\n" +
                "Dessert: " + dessert;
    }
}
