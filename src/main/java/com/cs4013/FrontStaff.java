package com.cs4013;

import java.util.ArrayList;

public class FrontStaff extends Staff{
    private ArrayList<Order> orders;

    FrontStaff(String name, int phoneNum){
        super(name, phoneNum);
        orders = new ArrayList<>();
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
    @Override
    public int getPhoneNum() {
        return super.getPhoneNum();
    }

    
    /** 
     * @param order
     * @param foods
     */
    public void createOrder(Order order, String[] foods) {
        for (int i = 0; i < foods.length; i++) {
            order.addMeal(foods[i]);
        }
        orders.add(order);
    }
}
