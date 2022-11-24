package com.cs4013;

public class OrderTest {

    public static void main(String[] args) {

        Menu menu = new Menu(1);
        Order order = new Order(menu);

        menu.addMeal("Porridge", "Starter", 10);
        menu.addMeal("Chicken", "Main", 10);

        System.out.println(menu);

        order.addMeal("Porridge");
        order.addMeal("Porridge");
        order.addMeal("Porridge");
        order.addMeal("Porridge");
        System.out.println(order);
    }
}
