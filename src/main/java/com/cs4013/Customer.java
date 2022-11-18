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
    Customer(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Customer customer = new Customer("John");
        customer.addReservation("today",12345,5,1,"now",1);
        customer.cancelation(12345);
    }

    public void addReservation(String date, int phoneNum, int guestNum, int restId, String time, int tableId) {
        res.addReservation(getName(), date, phoneNum, guestNum, restId, time, tableId);
    }

    public void cancelation(int phoneNum) {
        res.cancelation(phoneNum);
    }

    public void viewMenu() {
        men.toString();
    }

    public void viewTables(int restId, String time, String date) {
        //TODO add functionality
    }

    public void order() {
        Order order = new Order(men);
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
    }

    public void viewBill() {
        //TODO add functionality once bill class is created
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
