import java.util.ArrayList;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class Order {

    //Can only be Created, Waiting, Cancelled
    private String status;
    private ArrayList<Food> foodsOrdered = new ArrayList<>();
    private double totalPrice; //May be needed not sure yet
    private Menu menu;

    public Order(Menu menu) {
        this.menu = menu;
        totalPrice = 0;
    }

    public Order(Menu menu, ArrayList<Food> foodsOrdered) {
        this.menu = menu;
        this.foodsOrdered = foodsOrdered;
    }

    public void addFood(String foodName) {
        for(Food food : menu.getAllMeals()) {
            if(food.getName().equals(foodName)) {
                foodsOrdered.add(food);
                totalPrice += food.getPrice();
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

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order: " + foodsOrdered + "\n" +
                "Total Price: " + totalPrice;
    }
}
