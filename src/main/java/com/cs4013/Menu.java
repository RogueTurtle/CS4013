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

    /**
     * Creates a menu for the restaurant of restaurantID
     * @param restaurantID
     */
    public Menu(int restaurantID) {
        this.restaurantID = restaurantID;
        searchForMenu(restaurantID);

    }

    /**
     * Searches for menus already created for the restaurant ID
     * @param restaurantID The restaurant ID
     */

    private void searchForMenu(int restaurantID) {
        try {
            String line;
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

    /**
     * Adds a meal to the menu
     * @param name Name of meal
     * @param type Type of meal(starter, soups, main, dessert)
     * @param price Price of meal(double)
     */

    //This method is used to add a meal to allMeals and into one of the specified categories
    public void addMeal(String name, String type, double price) {
        boolean loop = true;
        boolean InMeals = false;
        for(Food meal : allMeals) {
            if(meal.getName().equalsIgnoreCase(name)) {
                InMeals = true;
                loop = false;
                System.out.println(meal.getName() + " is already on the menu");
            }
        }
        type = type.toUpperCase();
        Food newMeal = new Food(name, type, price);

        while(loop) {
            if(type.contains("STARTER")) {
                starters.add(newMeal);
                loop = false;
            }
            else if(type.contains("SOUP")) {
                soups.add(newMeal);
                loop = false;
            }
            else if(type.contains("MAIN")) {
                main_course.add(newMeal);
                loop = false;
            }
            else if(type.contains("DESSERT")) {
                dessert.add(newMeal);
                loop = false;
            }
            else {
                //Apparently not needed so can remove since I have exception in Food already - must remove food exception
                Scanner scan = new Scanner(System.in);
                System.out.println("Please input a valid type - 'STARTER', 'SOUP', 'MAIN', 'DESSERT' ");
                type = scan.next().toUpperCase();
            }
        }
        if(!InMeals) {
            allMeals.add(newMeal);
        }

        addToCSV(name, type, price);
    }

    
    /** 
     * @param name
     * @param type
     * @param price
     */
    private void addToCSV(String name, String type, double price) {
        try {
            FileWriter out = new FileWriter("src/storage/Menu.csv", true);
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("src/storage/Menu.csv"));
            boolean inMenuCSV = false;

            while((line = br.readLine()) != null) {
                String[] menus = line.split(",");
                    if(menus[1].equalsIgnoreCase(name)) {
                        inMenuCSV = true;
                        break;
                    }
            }
            if(!inMenuCSV && (line = br.readLine()) == "") {
                out.write(restaurantID + "," + name + "," + Double.toString(price) + "," + type);
            }
            else if(!inMenuCSV) {
                out.append(System.getProperty("line.separator") + restaurantID + "," + name + "," + Double.toString(price) + "," + type);
            }
            br.close();
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes meal from menu
     * @param meal The name of the meal you want to remove
     */

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
                deleteFromCSV(food.getName());
            }
        }
    }

    
    /** 
     * @return String
     */
    //Used to be of type Arraylist<Food>
    public String getStarters() {
        return "Starters: " + starters;
    }

    
    /** 
     * @return String
     */
    public String getSoups() {
        return "Soups: "  + soups;
    }

    
    /** 
     * @return String
     */
    public String getMainCourse() {
        return "Main Course: " + main_course;
    }

    
    /** 
     * @return String
     */
    public String getDessert() {
        return "Dessert: " + dessert;
    }

    
    /** 
     * @return ArrayList<Food>
     */
    public ArrayList<Food> getAllMeals() {
        return allMeals;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return  "Menu: " + "\n" +
                "Starters: " + starters + "\n" +
                "Soups: " + soups +"\n" +
                "Main Course: " + main_course +"\n" +
                "Dessert: " + dessert;
    }

    
    /** 
     * @param removeTerm
     */
    private void deleteFromCSV(String removeTerm) {
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

            Scanner scanner = new Scanner(new File("src/storage/menuTemp.csv"));
            FileWriter fw = new FileWriter("src/storage/Menu.csv", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            while(scanner.hasNext()) {
                pw.println(scanner.next()); //Copies all code from loginTemp to Login.
            }
            scanner.close();
            pw.flush();
            pw.close();
            tempFile.delete();

        }catch (Exception e) {

        }
    }
}
