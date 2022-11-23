package com.cs4013;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {

    private int restaurantID;
    private String name;
    private String password;
    private String phoneNum;
    private String authority;
    private String customerID;
    private Scanner scanner = new Scanner(System.in);
    private File accounts = new File("src/storage/accounts.csv");
    private HubInterface mainHub;

    /** Constructor for Account.
     *
     * @param mainHub Used to direct to MainHub at some point in the program.
     * @param restaurantID The restaurant ID to be set for future use.
     * @throws FileNotFoundException
     */
    public Account (HubInterface mainHub, int restaurantID) throws FileNotFoundException {
        this.mainHub = mainHub;
        this.restaurantID = restaurantID;
    }

    /**
     * Prompts the user with questions required to log them into their account.
     */
    public void login () {

        // prompt them with "Username: ", check that username exists in accounts.csv
        // if exists, prompt password and check that password matches.
        // if matches, set this object's details to the details in the csv
        int nameLine;
        String username = "";
        String[] information;

        do {
            System.out.println("LOG-IN\n" + "------------------");
            username = prompt("Username: ");
            nameLine = checkNameExists(username);
            if (nameLine >= 0) {
                break;
            }
            System.out.printf("Username \"%s\" is invalid. Please try again.\n", username);
        } while (true);
        information = comparePassword(username, nameLine);
        loginSuccess(information);

    }

    /** Checks whether a username is already taken on the system.
     *
     * @param username Inputted username.
     * @return An integer value used to determine either a false value (name doesn't exist),
     * or a positive value that indicates the line the name exists on.
     */
    private int checkNameExists (String username) {

        int i = 0;
        String[] line;

        try (FileReader fileRead = new FileReader(accounts);
             BufferedReader bufferRead = new BufferedReader(fileRead)) {
            bufferRead.readLine();
            while (bufferRead.ready()) {
                i++;
                line = bufferRead.readLine().split(",");
                if (username.equalsIgnoreCase(line[0])) {
                    return i;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;

    }


    /** Checks the stored password for a username and prompts the user to
     * correctly input the password to proceed.
     *
     * @param username Inputted username.
     * @param nameLine The line in the accounts.csv file the name is located.
     * @return String array containing all relevant information about user.
     */
    private String[] comparePassword (String username, int nameLine) {

        String line = "";
        String[] information;

        try (FileReader fileRead = new FileReader(accounts);
             BufferedReader bufferRead = new BufferedReader(fileRead)) {
            bufferRead.readLine();
            for (int i = 0; i < nameLine - 1; i++) {
                bufferRead.readLine();
            }
            line = bufferRead.readLine();
            information = line.split(",");
            while (true) {
                line = prompt("Password: ");
                if (line.equals(information[1])) {
                    return information;
                }
                System.out.println("--- Invalid password, please try again. ---");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"unverified"};
    }

    /** Sets account's values and directs user to the main hub.
     *
     * @param information A string array containing all details about user.
     */
    private void loginSuccess (String[] information) {

        this.name = information[0];
        this.password = information[1];
        this.phoneNum = information[2];
        this.authority = information[3];
        this.customerID = (information[0] + "_" + information[2]);

        mainHub.options(this);

    }

    /**
     * Prompts the user with multiple questions and uses information from responses
     * to add a new account to the system.
     */
    public void signUp () {

        String username = validateUsername();
        String phoneNum = validatePhoneNumber();
        String password = validatePassword();
        addAccount(username, password, phoneNum);

    }

    /**
     *
     * @param username Username of new account.
     * @param password Password of new account.
     * @param phoneNum Phone number of new account.
     */
    private void addAccount (String username, String password, String phoneNum) {

        String csvLine = String.format("%s,%s,%s,CUSTOMER,%s_%s", username, password, phoneNum, username, phoneNum);

        try (FileWriter fileWrite = new FileWriter(accounts, true)) {
            fileWrite.write("\n" + csvLine);
            System.out.println(csvLine);
            String[] information = { username, password, phoneNum, "CUSTOMER" };
            loginSuccess(information);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** Validates whether a username is acceptable.
     *
     * @return Valid username.
     */
    private String validateUsername () {

        String username = "";
        String[] line;
        boolean taken = false;

        try (FileReader fileRead = new FileReader(accounts);
             BufferedReader bufferRead = new BufferedReader(fileRead)) {
            bufferRead.readLine();
            while (true) {
                System.out.println("SIGN-UP\n" + "------------------");
                username = prompt("Username (3-20 characters): ").toLowerCase();
                if (username.length() < 3 || username.length() > 20) {
                    System.out.println("--- Invalid username, please try again. ---");
                    continue;
                }
                if (checkNameExists(username) == -1) {
                    return username;
                }
                System.out.println("--- Username already taken, please try again. ---");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    /** Validates whether a password is acceptable.
     *
     * @return Valid password.
     */
    private String validatePassword () {

        String password = "";

        while (true) {
            password = prompt("""
                Password must contain at least:
                - 8 characters.
                - 1 lowercase letter.
                - 1 uppercase letter.
                - 1 number.
                
                Enter password:\040""");
            if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(.+){8,}$", password)) {
                return password;
            }
            System.out.println("--- Invalid password, please try again. ---");
        }

    }

    /** Validates whether a phone number is acceptable.
     *
     * @return Valid phone number.
     */
    private String validatePhoneNumber () {

        String phoneNum = "";

        while (true) {
            phoneNum = prompt("Phone number (used for reservations): ").replaceAll(" ", "");
            try {
                Long validPhoneNumber = Long.parseLong(phoneNum);
                return phoneNum;
            } catch (NumberFormatException e) {
                System.out.println("--- Invalid phone number, please enter digits only. ---");
            }
        }

    }

    /** Promotes a user's authority level (can only be called by OWNER).
     *
     * @param username Username of user to be promoted.
     * @param promotion Level to promote user to ("CHEF", "FRONT_STAFF", "OWNER")
     * @return
     */
    private boolean promoteUser (String username, String promotion) {
        return true;
    }

    /** Prompts the user with a question and returns a trimmed string input.
     *
     * @param prompt A question to prompt the user.
     * @return A trimmed string inputted by the user.
     */
    private String prompt (String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Returns the current username.
     *
     * @return The current username.
     */
    public String getName () {
        return name;
    }

    /** Returns the current password.
     *
     * @return The current password.
     */
    public String getPassword () {
        return password;
    }

    /** Returns the current phone number.
     *
     * @return The current phone number.
     */
    public String getPhoneNum () {
        return phoneNum;
    }

    /** Returns the current authority level.
     *
     * @return The current authority level.
     */
    public String getAuthority () {
        return authority;
    }

    /** Returns the current customerID.
     *
     * @return The current customerID.
     */
    public String getCustomerID() {
        return customerID;
    }

    /** Returns the current restaurant ID.
     *
     * @return The current restaurant ID.
     */
    public int getRestaurantID () {
        return restaurantID;
    }
}