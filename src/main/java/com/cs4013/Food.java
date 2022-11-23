package com.cs4013;

public class Food {

    private String name;
    private String type;
    private String description;
    private double price;

    public Food(String name, String type, double price) {
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

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": €" + price;
    }

    public double getPrice() {
        return price;
    }
}


