package com.cs4013;

import java.util.Scanner;

public class MainHubInterface implements HubInterface {

    private Scanner scanner = new Scanner(System.in);

    /** Displays various options to the user to move through the program.
     *
     * @param account Account object to access user details.
     */
    @Override
    public void options (Account account) {

        int response = 0;

        do {
            System.out.printf("""
                Welcome %s! What would you like to do?
                (1) Reservations
                (2) Check menu
                (3) Admin (requires authority)
                >\040""", account.getName());
            try {
                String input = scanner.nextLine().trim();
                response = Integer.parseInt(input);
                if (response < 1 || response > 3) {
                    System.out.println("--- Invalid value, please try again. ---");
                }
            } catch (Exception e) {
                System.out.println("--- Invalid value, please try again. ---");
            }
        } while (response < 1 || response > 3);
        switch (response) {
            case (1): {
                // go to reservations
                HubInterface reservations = new Reservations(new MainHubInterface());
                reservations.options(account);
                break;
            }
            case (2): {
                // print menu
                Menu menu = new Menu(account, new MainHubInterface());
                menu.printMenu();
                break;
            }
            case (3): {
                // go to admin
                if (!account.getAuthority().equals("CUSTOMER")) {
                    Admin admin = new Admin(new MainHubInterface());
                    admin.options(account);
                }
            }
        }

    }

}
