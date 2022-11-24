package com.cs4013;

public class Chef extends Staff{
    Chef(String name, int phoneNum) {
      super(name, phoneNum);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getPhoneNum() {
        return super.getPhoneNum();
    }

    /**
     * Changes the status of an order
     * @param status
     * @param order
     */

    public void changeOrderStatus(String status, Order order) {
        order.changeStatus(status);
    }
}
