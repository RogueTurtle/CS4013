package com.cs4013;

import java.util.ArrayList;

public class Owner extends Staff{
    private ArrayList<Order> orders;

    public Owner(String name, int phoneNumber) {
        super(name, phoneNumber);

    }
    public void createOrder(Order order, String[] foods) {
        for (int i = 0; i < foods.length; i++) {
            order.addMeal(foods[i]);
        }
        orders.add(order);
    }

    public void changeOrderStatus(String status, Order order) {
        order.changeStatus(status);
    }
}
