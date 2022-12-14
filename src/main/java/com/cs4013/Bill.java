package com.cs4013;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

//Author Stephen Walsh :21334234

public class Bill {

    private String receipt;
    private double totalPrice;
    private String payMethod;
    private Order order;
    private double tipAmount;


    
    /** 
     * @return double
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    public Bill(Order order, String payMethod) {

        this.order = order;
        this.payMethod = payMethod;
        totalPrice = order.getTotalPrice();
    }


    
    /** 
     * @return String
     */
    public String getReceipt() {
        return receipt;
    }

    
    /** 
     * @param receipt
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    
    /** 
     * @param amountPaid
     * 
     */
    public void payment(Double amountPaid) {
        Scanner scan = new Scanner(System.in);
        System.out.println("DO you want to add a tip? y/n: ");
        String tip = scan.nextLine();
        double price = order.getTotalPrice();
        if (tip.equals("y")|| tip.equals("Y")) {
            System.out.println("Home much would you like to tip");
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

        income(price);
    }

    
    /** 
     * @param price
     * generates a running total of income generated
     */
    public void income(double price) {
        File income = new File("src/storage/RunningIncome.csv");
        String incomeString = "";
        double runningIncome = 0;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String finalString = "";
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
            finalString = incomeString + "," + formatter.format(today);
            bw.write(finalString);
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * @return String
     */
    // To String Method
    @Override
    public String toString(){
        return String.format("Thank you for visiting");
                                            // Both of these names can be changed it's just to get the idea
    }
    
}