package com.cs4013;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
    private int restaurantId;
    private Menu menu;
    private ArrayList<Account> accounts;
    private Reservation reservation;
    private ArrayList<Chef> chefs;
    private Guests[] guests; //Needed or nah?
    //private Customer[] customers;
    private ArrayList<Order> orders;
    private ArrayList<FrontStaff> frontStaffs;
    private ArrayList<Owner> owners;
    private static Scanner scanner;
    private Account lastUsedAccount;

    /**
     * Creates a restaurant
     * @param restaurantId
     */

    public Restaurant(int restaurantId) {
        this.restaurantId = restaurantId;
        menu = new Menu(restaurantId);
        scanner = new Scanner(System.in);
        accounts = new ArrayList<>();
        orders = new ArrayList<>();
        chefs = new ArrayList<>();
        frontStaffs = new ArrayList<>();
        lastUsedAccount = new Account();
        owners = new ArrayList<>();
    }

    /**
     * Takes the user to the restaurant login page
     */

    public void restaurantLoginPage() {
        int response;
        response = mainPrompt("Please enter Restaurant ID: ");
        restaurantId = response;

        while(!lastUsedAccount.isLoggedIn()) {
            Account account = new Account();
            lastUsedAccount = account;
            response = mainPrompt("""
                     What Would you like to do?
                     (1) Log-in
                     (2) Sign-up
                     >\040""", 1, 2);
            switch (response) {
                case (1): {
                    // go to login
                    account.login();
                    break;
                }
                case (2): {
                    // go to sign up
                    account.createAccount();
                    accounts.add(account);
                }
            }
        }
        accounts.add(lastUsedAccount);
        if(lastUsedAccount.getLevel() == 6) {
            Owner owner = new Owner(lastUsedAccount.getName(), 282);
            owners.add(owner);

        }
        else if(lastUsedAccount.getLevel() == 4) {
            Chef chef = new Chef(lastUsedAccount.getName(), 344 ); //Phonenumber just a test
            chefs.add(chef);

        }
        else if(lastUsedAccount.getLevel() == 3) {
            FrontStaff fs1 = new FrontStaff(lastUsedAccount.getName(), 2344);
            frontStaffs.add(fs1);
        }
        mainMenu();
    }
    /** Prompts the user with a multiple choice question.
     * Validates whether their answer is acceptable.
     *
     * @param prompt A question to prompt the user.
     * @return The value for their selected option.
     */
    private static int mainPrompt (String prompt) {

        do {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("--- Invalid value, please try again. ---");
            }
        } while (true);

    }

    /** Prompts the user with a multiple choice question.
     * Validates whether their answer is acceptable.
     *
     * @param prompt A question to prompt the user.
     * @param min Minimum value to validate against.
     * @param max Maximum value to validate against.
     * @return The value for their selected option.
     */

    private static int mainPrompt (String prompt, int min, int max) {

        do {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                int response = Integer.parseInt(input);
                if (response >= min || response <= max) {
                    return response;
                }
            } catch (Exception e) {
                System.out.println("--- Invalid value, please try again. ---");
            }
        } while (true);

    }

    /**
     * Restaurant's main menu
     */
    public void mainMenu() {
        if(lastUsedAccount.isLoggedIn()) {
            int response = mainPrompt("""
                Welcome! What would you like to do?
                (1) Reservations
                (2) Check menu
                (3) Admin (requires authority)
                >""",1 , 3);
            switch (response) {
                case (1): {
                    Reservation res = new Reservation();
                    System.out.print(res.showReservation() + "\n");
                    mainMenu();
                    break;
                }
                case (2):{
                    System.out.println(menu);
                    mainMenu();
                    break;
                }
                case (3):{
                    if (lastUsedAccount.getLevel() >= 3) {
                        adminMenu();
                    } else {
                        System.out.println("--- You do not have permission. ---");
                        mainMenu();
                    }
                }
            }
        }
    }

    /**
     * Restaurants admin menu
     */

    public void adminMenu() {
        int response = mainPrompt("""
                What would you like to do?
                (1) Promote/Demote a user
                (2) Create an order
                (3) Change order status
                (4) Reserve a table
                (5) Go back
                >""", 1, 5);
        switch (response) {
            case (1): {
                // Promote a user (OWNER only)
                if (lastUsedAccount.getLevel() == 6) {
                    System.out.println("Please input the name of the account: ");
                    String username = scanner.next();
                    boolean levelChosen = false;
                    for(Account account : accounts) {
                        if(account.getName().equalsIgnoreCase(username)) {
                            while(!levelChosen) {
                                System.out.println("Please input the new level to be granted to " + username);
                                int level = scanner.nextInt();
                                if(level < 6 && level >= 0) {
                                    lastUsedAccount.setLevel(account, level);
                                    levelChosen = true;
                                } else {
                                    System.out.println("Invalid level");
                                    System.out.printf("""
                                        Level 1: Customer
                                        Level 2: Waiter
                                        Level 3: Front Staff
                                        Level 4: Chef
                                        Level 5: Manager
                                        Level 6: Owner
                                        """);
                                }
                            }
                        }
                        else {
                            System.out.println("No account called " + username);
                        }
                    }
                }
                adminMenu();
                break;
            }
                case (2): {
                    // Create an order (FRONT_STAFF or OWNER)
                    Order order = new Order(menu);
                    if (lastUsedAccount.getLevel() == 3 || lastUsedAccount.getLevel() == 6) {
                        if(frontStaffs.isEmpty()) {
                            System.out.println("There are no front staff currently working");;
                        }
                        for(FrontStaff fs : frontStaffs) {
                            if(lastUsedAccount.getName().equalsIgnoreCase(fs.getName())) {
                                System.out.println(menu);
                                System.out.println("Name the meals ordered: ");
                                String line = scanner.nextLine();
                                String[] foods = line.split(",");
                                fs.createOrder(order, foods);
                                System.out.println(order);
                            }
                        }
                    }
                    else {
                        System.out.println("You do not have permissions here");
                    }
                    adminMenu();
                    break;
                }
                case (3): {
                    if(lastUsedAccount.getLevel() == 4 || lastUsedAccount.getLevel() == 6) {
                        if(chefs.isEmpty()) {
                            System.out.println("There are no chefs working right now");
                        }
                        for(Chef chef : chefs) {
                            if(lastUsedAccount.getName().equalsIgnoreCase(chef.getName())) {
                                if(orders.isEmpty()) {
                                    System.out.println("There are currently no orders");
                                }
                                for(Order order : orders) {
                                    System.out.println(orders.indexOf(order) + ": " + order);
                                }
                                System.out.println("What order would you like to change?");
                                int orderNumber = scanner.nextInt();
                                if(orders.get(orderNumber) != null) {
                                    System.out.println("What is the status of the order?");
                                    String status = scanner.next();
                                    orders.get(orderNumber).changeStatus(status);
                                }
                                else {
                                    System.out.println("There is no order with this order number!");
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("You do not have permissions here");
                    }
                    adminMenu();
                    break;
                }
                case (4): {
                    
                    System.out.println("Please enter table number: ");
                    int tableNo = scanner.nextInt();
                    System.out.println("Please enter name for reservation: ");
                    String name = scanner.next();
                    System.out.println("Please Enter phone number for reservation: ");
                    int number = scanner.nextInt();
                    System.out.println("Please enter the number of people in attendance: ");
                    int noPeople = scanner.nextInt();
                    System.out.println("Please Enter the date of the reservation (dd/mm/yy): ");
                    String date = scanner.next();
                    System.out.print("Please enter the time for reservation (e.g 12:45): ");
                    String time = scanner.next();
                    Reservation res = new Reservation();
                    res.addReservation(name, date, number, noPeople, restaurantId, time, tableNo);
                    adminMenu();
                    break;
                }
                case (5): {
                   // Go back to MainHub
                   mainMenu();
                }
            }
    }
}
