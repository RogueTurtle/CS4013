package com.cs4013;

public class Food {

    private String name;
    private String type;
    private String description;
    private double price;

    public Food(String name, String type, String description, double price) {
        if(type.toUpperCase().contains("STARTER")) {
            this.type = "STARTER";
        }
        else if(type.toUpperCase().contains("SOUP")) {
            this.type = "SOUP";
        }
        else if(type.toUpperCase().contains("MAIN")) {
            this.type = "MAIN COURSE";
        }
        else if(type.toUpperCase().contains("DESSERT")) {
            this.type = "DESSERT";
        }
        else {
            throw new RuntimeException("Error: Food not added as no adequate type was stated.");
        }
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": €" + price;
    }
}
