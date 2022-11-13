import java.util.Scanner;

public class Bill {
    /***
     *
    Scanner scan = new Scanner(System.in); This will prob have to go into the main class if we want to incorporate tips
    System.out.println("Please enter tip amount: ")
     double tipAmount = scan.nextDouble();//Optional Tip

     **/

    private String receipt;
    private Order order; //Pulling the order from the Order class
    private double orderPrice;
    double tipAmount;


    public double getOrderPrice() {
        return orderPrice;
    }

    public Bill(Order order){
        this.order = order;
    }

    public String getReceipt() {
        return receipt;
    }

    // Printing out the receipt basically for the customer
    @Override
    public String toString(){
        return String.format("Your total for today is " + (orderPrice + tipAmount) + " Thank you for visiting.");
                                            // Both of these names can be changed it's just to get the idea
    }

}
