package com.cs4013;
//adds and cancels reservation
//view menu
//create order
//views bill
//view tables

import java.util.Scanner;

public class Customer extends Person{

    Reservation res = new Reservation();
    Menu men = new Menu();
    Customer(String name, int phoneNum) {
        super(name, phoneNum);
    }

    public static void main(String[] args) {
        Customer customer = new Customer("John", 12345);
        customer.addReservation("today",5,1,"now",1);
        customer.cancelation(12345);
    }

    public void addReservation(String date, int guestNum, int restId, String time, int tableId) {
        res.addReservation(getName(), date, getPhoneNum(), guestNum, restId, time, tableId);
    }

    public void cancelation(int phoneNum) {
        res.cancelation(phoneNum);
    }

    public void viewMenu() {
        men.toString();
    }

    public void viewTables(int restId, String time, String date) {
        res.tables(restId, time, date);
    }

    public Order order() {
        Order order = new Order();
        String quit = "";
        Scanner scan = new Scanner(System.in);
        String foodString = "";
        while (quit!="q" || quit!="Q") {
            System.out.println("Please enter the name of the food item you wish to order or Enter q to finish and exit ");
            foodString = scan.nextLine();
            if (foodString == "q" || foodString == "Q") {
                quit = foodString;
                scan.close();
            } else {
                order.addFood(foodString);
            }
        }
        return order;
    }

    public void getBill(String paymentMethod) {
        Bill bill = new Bill(order(), paymentMethod);
        bill.getReceipt();
    }

    @Override
    public String getName() {
        return super.getName();
    }
    public int getPhoneNum() {
        return super.getPhoneNum();
    }
}