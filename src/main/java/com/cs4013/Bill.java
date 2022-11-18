package com.cs4013;
import java.util.Objects;
import java.util.Scanner;

//Author Stephen Walsh :21334234

public class Bill {
    /***
     *
     Scanner scan = new Scanner(System.in); This will prob have to go into the main class if we want to incorporate tips
     System.out.println("Please enter tip amount: ")
     double tipAmount = scan.nextDouble();//Optional Tip
     **/

    private String receipt;
    private Order order; //Pulling the order from the Order class
    private double price;
    private String payMethod;
    double tipAmount;


    public double getPrice() {
        return price;
    }

    public Bill(Order order, String payMethod) {

        this.order = order;
        this.payMethod = payMethod;
        this.order = order.getPrice(); //Would need a getPrice method in the order class
    }


    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public double payment() {
        if (Objects.equals(payMethod, "Cash")) {
            price = price + tipAmount;
            if (price < 0) {
                price = -price; //Change
                System.out.println("Your change is " + price);
                return price;
            }

        } else System.out.println("Incorrect amount of money");

        if (payMethod == "Card") {
            price = price + tipAmount;
            System.out.println("Transaction Verified");
        } else System.out.println("Void Transaction");
        return 0;
    }


    // Printing out the receipt basically for the customer
    @Override
    public String toString(){
        return String.format("Your total for today is " + (getPrice() + tipAmount) + " Thank you for visiting.");
                                            // Both of these names can be changed it's just to get the idea
    }

}