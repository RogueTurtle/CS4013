package com.cs4013;

public class OrderTest {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Order order = new Order(menu);

        menu.addMeal("Porridge", "Starter", 10);
        menu.addMeal("Chicken", "Main", 10);

        System.out.println(menu);

        order.addFood("Porridge");
        order.addFood("Porridge");
        order.addFood("Porridge");
        order.addFood("Porridge");
        System.out.println(order);
    }
}
