package com.cs4013; /**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private int restaurantID;
    private ArrayList<Food> starters = new ArrayList<>();
    private ArrayList<Food> soups = new ArrayList<>();
    private ArrayList<Food> main_course = new ArrayList<>();
    private ArrayList<Food> dessert = new ArrayList<>();
    private ArrayList<Food> allMeals = new ArrayList<>();

    public Menu(int restaurantID) {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("src/storage/Menu.csv"));

            while((line = br.readLine()) != null) {
                String[] menus = line.split(",");
                if(restaurantID == Integer.parseInt(menus[0])) {
                        addMeal(menus[1], menus[3], Double.parseDouble(menus[2]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lookForMenu() {

    }

    //This method is used to add a meal to allMeals and into one of the specified categories
    public void addMeal(String name, String type, double price) {
        Food newMeal = new Food(name, type, price);

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
        allMeals.add(newMeal);

        try {
            FileWriter out = new FileWriter("src/storage/Menu.csv", true);
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("src/storage/Menu.csv"));

            while((line = br.readLine()) != null) {
                String[] menus = line.split(",");
                if(restaurantID == Integer.parseInt(menus[0])) {
                    if(!menus[1].equalsIgnoreCase(name)) {
                        out.append(restaurantID + "," + name + "," + Double.toString(price) + "," + type);
                    }
                }
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
            }
        }
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

    public void deleteFromCSV(String removeTerm) {
        File tempFile = new File("src/storage/menuTemp.csv");
        File realFile = new File("src/storage/Menu.csv");
        String line = "";

        try {
            FileWriter fileWriter = new FileWriter("src/storage/menuTemp.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            FileReader fileReader = new FileReader("src/storage/Menu.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] menus = line.split(",");
                if(!menus[1].equalsIgnoreCase(removeTerm)) {
                    printWriter.println(menus[0] + "," + menus[1] + "," + menus[2] + "," + menus[3] + "\n");
                }
            }
            printWriter.flush();
            printWriter.close();
            fileReader.close();
            bufferedReader.close();
            bufferedWriter.close();
            fileWriter.close();



        }catch (Exception e) {

        }
    }
}
