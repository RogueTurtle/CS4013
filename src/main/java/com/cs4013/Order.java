package com.cs4013;

import java.util.Scanner;

public class Order implements HubInterface {

    private int response;
    private Scanner scanner = new Scanner(System.in);
    private HubInterface mainHub;

    public Order (HubInterface mainHub) {
        this.mainHub = mainHub;
    }

    @Override
    public void options(Account account) {
        response = prompt("""
                What would you like to do?""");
    }

    public int prompt(String prompt) {
        return 0;
    }
}
//Maybe have 3 methods for displaying and 3 methods for adding starters, main and  desserts
//When an order is finish, call "finaliseOrder()" and calculate the total cost.
//Order might be persistent data if we wanna handle refunds but it seems like itd be a little messy to store for our capabilities
//We can add an Order object to an arraylist maybe and then a "CHEF" can change status to complete
//When an Order is set to complete we call a method to Bill the customerID (what if we have a walk-in customer?)