package com.cs4013;

import java.io.File;
import java.util.Scanner;

public class Admin implements HubInterface {

    private HubInterface mainHub;
    private Scanner scanner = new Scanner(System.in);
    private int response;

    /** Constructor for Admin.
     *
     * @param mainHub Used to direct to MainHub at some point in the program.
     */
    public Admin (HubInterface mainHub) {
        this.mainHub = mainHub;
    }

    /** Displays various options to the user to move through the program.
     *
     * @param account Account object to access user details.
     */
    @Override
    public void options(Account account) {
        response = prompt("""
                What would you like to do?
                (1) Promote a user
                (2) Create an order
                (3) Change order status
                (4) Reserve a table
                (5) Generate income statistics
                (6) Go back
                >\040""", 1, 6);
        switch (response) {
            case (1): {
                // Promote a user (OWNER only)
                if (account.getAuthority().equals("OWNER")) {
                    File file = new File("src/storage/reservationReset.csv");
                    file.delete();
                }
                break;
            }
            case (2): {
                // Create an order (FRONT_STAFF or OWNER)
                if (account.getAuthority().equals("OWNER") || account.getAuthority().equals("FRONT_STAFF")) {
                    Order order = new Order(new MainHubInterface());
                    order.options(account);
                }

                break;
            }
            case (3): {
                // Change order status (CHEF or OWNER)
                break;
            }
            case (4): {
                // Reserve a table (FRONT or OWNER)

                break;
            }
            case (5): {
                // Generate income statistics (OWNER only)

                break;
            }
            case (6): {
                // Go back to MainHub

            }
        }
    }

    /** Prompts the user with a multiple choice question.
     * Validates whether their answer is acceptable.
     *
     * @param prompt A question to prompt the user.
     * @param min Minimum value to validate against.
     * @param max Maximum value to validate against.
     * @return The value for their selected option.
     */
    private int prompt (String prompt, int min, int max) {


        String input = "";
        int response = 0;

        do {
            try {
                System.out.print(prompt);
                input = scanner.nextLine().trim();
                response = Integer.parseInt(input);
                if (response >= min || response <= max) {
                    return response;
                }
            } catch (Exception e) {
                System.out.println("--- Invalid value, please try again. ---");
            }
        } while (true);

    }

}
