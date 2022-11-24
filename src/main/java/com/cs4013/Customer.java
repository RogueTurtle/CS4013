package com.cs4013;
//adds and cancels reservation
//view menu
//create order
//views bill
//view tables

import java.util.Scanner;

public class Customer extends Person{

    Reservation res = new Reservation();
    Menu men = new Menu(1);
    Customer(String name, int phoneNum) {
        super(name, phoneNum);
    }

    
    /** 
     * @param args
     */

    
    /** 
     * @param date
     * @param guestNum
     * @param restId
     * @param time
     * @param tableId
     */
    public void addReservation(String date, int guestNum, int restId, String time, int tableId) {
        res.addReservation(getName(), date, getPhoneNum(), guestNum, restId, time, tableId);
    }

    
    /** 
     * @param phoneNum
     */
    public void cancelation(int phoneNum) {
        res.cancelation(phoneNum);
    }

    public void viewMenu() {
        men.toString();
    }

    
    /** 
     * @param restId
     * @param time
     * @param date
     */
    public void viewTables(int restId, String time, String date) {
        res.tables(restId, time, date);
    }

    
    /** 
     * @return Order
     */
    public Order order() {
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
                order.addMeal(foodString);
            }
        }
        return order;
    }

    
    /** 
     * @param paymentMethod
     */
    public void getBill(String paymentMethod) {
        Bill bill = new Bill(order(), paymentMethod);
        bill.getReceipt();
    }

    
    /** 
     * @return String
     */
    @Override
    public String getName() {
        return super.getName();
    }
    
    /** 
     * @return int
     */
    public int getPhoneNum() {
        return super.getPhoneNum();
    }
}