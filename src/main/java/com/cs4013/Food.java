package com.cs4013;

import java.text.NumberFormat;

public class Food {

    private String name;
    private String type;
    private double price;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    protected Food(String name, String type, double price) {
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

    protected String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + currency.format(price);
    }

    protected double getPrice() {
        return price;
    }
}


