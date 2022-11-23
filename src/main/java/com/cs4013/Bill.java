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
    /***
     *
     Scanner scan = new Scanner(System.in); This will prob have to go into the main class if we want to incorporate tips
     System.out.println("Please enter tip amount: ")
     double tipAmount = scan.nextDouble();//Optional Tip
     **/

    private String receipt;
    private double price;
    private String payMethod;
    double tipAmount;


    public double getPrice() {
        return price;
    }

    public Bill(Order order, String payMethod) {

        this.payMethod = payMethod;
        price = order.getPrice(); //Would need a getPrice method in the order class
    }


    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public double payment() {
        Scanner scan = new Scanner(System.in);
        System.out.println("DO you want to add a tip? y/n: ");
        String tip = scan.nextLine();
        if (tip == "y"|| tip == "Y") {
            System.out.println("Home much would you like to tip");
            tipAmount = scan.nextDouble();
            scan.close();
        } else {
            tipAmount = 0;
        }
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

    // Printing out the receipt basically for the customer
    @Override
    public String toString(){
        return String.format("Your total for today is " + (getPrice() + tipAmount) + " Thank you for visiting.");
                                            // Both of these names can be changed it's just to get the idea
    }

}