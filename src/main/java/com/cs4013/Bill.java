package com.cs4013;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

//Author Stephen Walsh :21334234

public class Bill {
    
    private String receipt;
    private double totalPrice;
    private String payMethod;
    private Order order;
    private double tipAmount;


    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * creates a bill with a pay method and an order in mind
     * @param order coming from the order class will contain the price
     * @param payMethod either cash or card payment type
     */
    public Bill(Order order, String payMethod) {

        this.order = order;
        this.payMethod = payMethod;
        totalPrice = order.getTotalPrice();
    }



    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * method for a customer paying for their order
     * @param amountPaid The amount of Money the customer has given us before anything else
     */
    public void payment(Double amountPaid) {
        Scanner scan = new Scanner(System.in);
        System.out.println("DO you want to add a tip? y/n: ");
        String tip = scan.nextLine();
        double price = order.getTotalPrice();
        if (tip.equals("y")|| tip.equals("Y")) {
            System.out.println("How much would you like to tip");
            tipAmount = scan.nextDouble();
            scan.close();
        } else {
            tipAmount = 0;
        }


        if (Objects.equals(payMethod, "Cash")) {
            totalPrice = price + tipAmount;
            if (totalPrice <= amountPaid) {
                amountPaid -= totalPrice; //Change
                System.out.println("Your change is " + amountPaid);
            } else {
                System.out.println("Insufficient Money.");
            }
        }else if (Objects.equals(payMethod, "Card")) {
            totalPrice = amountPaid + tipAmount;
            System.out.println("Transaction Verified");
        } else {System.out.println("Void Transaction");}


    }

    /**
     * Stores data in csv file
     * @param price Cost of orders
     */
    public void income(double price) {
        File income = new File("src/storage/RunningIncome.csv");
        String incomeString = "";
        double runningIncome = 0;
        //Read Running Income and add price to running total then write total to file
        try {
            FileReader fr = new FileReader(income);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(income);
            BufferedWriter bw = new BufferedWriter(fw);
            incomeString = br.readLine();
            runningIncome = Double.parseDouble(incomeString);
            runningIncome += price;
            incomeString = String.valueOf(runningIncome);
            bw.write(incomeString);
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // To String Method
    @Override
    public String toString(){
        return String.format("Thank you for visiting");
        // Both of these names can be changed it's just to get the idea
    }

}