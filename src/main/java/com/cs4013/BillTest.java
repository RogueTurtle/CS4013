package com.cs4013;

public class BillTest {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Menu menu = new Menu(1);
        Order order = new Order(menu);
        Bill bill = new Bill(order, "Cash");

        menu.getStarters();
        System.out.println(menu);
        menu.addMeal("Porridge", "Starter", 10);
        menu.addMeal("Chicken", "Main", 10);
        System.out.println("");

        order.addMeal("Chicken");
        System.out.println(order);
        System.out.println("");
        bill.payment(30.00);
        System.out.println();

        System.out.println(bill);

    }
}