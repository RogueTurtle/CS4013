

import java.util.ArrayList;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class Order {

    //Can only be Created, Waiting, Cancelled
    private String status;
    private ArrayList<Food> foodsOrdered = new ArrayList<>();
    private double moneyRequired; //May be needed not sure yet
    private Menu menu;

    public Order(Menu menu) {
        this.menu = menu;
    }

    public Order(Menu menu, ArrayList<Food> foodsOrdered) {
        this.menu = menu;
        this.foodsOrdered = foodsOrdered;
    }

    public void addFood(String foodName) {
        for(Food food : menu.getAllMeals()) {
            if(food.getName().equals(foodName)) {
                foodsOrdered.add(food);
            }
        }
    }

    //Trying to make changing statuses flexible
    public void changeStatus(String status) {
        String newStatus = status.toUpperCase();
        if(newStatus.contains("CREATE") || newStatus.contains("MADE") || newStatus.contains("ORDERED")) {
            this.status = "CREATED";
        }
        else if(newStatus.contains("WAIT") || newStatus.contains("MADE") || newStatus.contains("TRANSIT")) {
            this.status = "WAITING";
        }
        else if(newStatus.contains("CANCEL") || newStatus.contains("STOP") || newStatus.contains("ABORT")) {
            this.status = "CANCELLED";
        }
        else {
            throw new RuntimeException("Error, status not valid");
        }
    }


}
