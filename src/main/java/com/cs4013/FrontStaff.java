package com.cs4013;

import java.util.ArrayList;

public class FrontStaff extends Staff{
    private ArrayList<Order> orders;

    FrontStaff(String name, int phoneNum){
        super(name, phoneNum);
        orders = new ArrayList<>();
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public int getPhoneNum() {
        return super.getPhoneNum();
    }

    public void createOrder(Order order, String[] foods) {
        for (int i = 0; i < foods.length; i++) {
            order.addMeal(foods[i]);
        }
        orders.add(order);
    }
}
