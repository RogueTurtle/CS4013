package com.cs4013;

public class BillTest {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order(menu);
        Bill bill = new Bill(order, "Cash");

        menu.getStarters();
        System.out.println(menu);
        menu.addMeal("Porridge", "Starter", "Our fine porridge", 10);
        menu.addMeal("Chicken", "Main", "Our fine porridge", 10);
        System.out.println("");

        order.addFood("Chicken");
        System.out.println(order);
        System.out.println("");
        bill.payment(30.00);
        System.out.println();

        System.out.println(bill);

    }
}