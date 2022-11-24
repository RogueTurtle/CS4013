package com.cs4013;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int restaurantId;
        int response;
        response = mainPrompt("Please enter Restaurant ID: ");
        restaurantId = response;

        Account account = new Account();
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
            }
        }


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
}
