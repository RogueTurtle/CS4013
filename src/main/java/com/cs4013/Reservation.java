package com.cs4013;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Reservation {

    private Scanner scanner = new Scanner(System.in);
    private File reservations = new File("src/storage/Reservations.csv");
    private File tables = new File("src/storage/Tables.csv");
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    private int restaurantID;
    private Account lastUsedAccount;

    public Reservation (int restaurantID, Account lastUsedAccount) {
        this.restaurantID = restaurantID;
        this.lastUsedAccount = lastUsedAccount;
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

    /** Prompts the user for a date value in the format "uuuu-MM-dd HH:mm".
     *
     * @return The value they inputted.
     */
    public void promptDate() {

        String input = "";
        LocalDateTime response;

        do {
            try {
                System.out.print("""
                        Please enter the date and time you'd like to reserve
                        (in the format of yyyy-mm-dd hh:mm, e.g. 2022-12-20 18:45 ):\040""");
                input = scanner.nextLine().trim();
                response = LocalDateTime.parse(input, dateTimeFormat);
                // could cause problems if formats dont match
                if (!(response.isBefore(LocalDateTime.now()))) {
                    break;
                }
                System.out.println("--- That time has passed! Please try again. ---");
            } catch (Exception e) {
                System.out.println("--- Invalid value, please try again. ---");
            }
        } while (true);

        printAvailableTables(response);
    }

    /** Prints out any available tables within a 2-hour window of a certain time.
     *
     * @param date Date is used to check for tables that are reserved.
     */
    private void printAvailableTables (LocalDateTime date) {

        System.out.println("""
                Available tables:
                Table Number | Capacity""");
        ArrayList<String> availableTables = checkAvailableTables(date);
        for (String s : availableTables) {
            System.out.println(s);
        }
        reserveTable(availableTables, date);

    }

    /** Choose a table to reserve.
     *
     * @param availableTables Tables that are available to reserve.
     * @return Int array containing the row where the table number and capacity are located.
     */
    private int[] chooseTable(ArrayList<String> availableTables) {

        String firstLine = availableTables.get(0);
        String lastLine = availableTables.get(availableTables.size() - 1);
        int tableChosen = 0;

        do {
            int i = 0;
            tableChosen = prompt("Please enter the Table Number of the table you'd like to reserve: ",
                    Integer.parseInt(firstLine.substring(0, firstLine.indexOf(","))),
                    Integer.parseInt(lastLine.substring(0, lastLine.indexOf(","))));
            for (String s : availableTables) {
                if (Integer.parseInt(s.substring(0, s.indexOf(","))) == tableChosen) {
                    return new int[]{ i, tableChosen };
                }
                i++;
            }
            System.out.println("--- Invalid value, please try again. ---");
        } while (true);

    }

    /** Reserves a table.
     *
     * @param availableTables Tables that are available to reserve.
     * @param date Date/Time that the table will be reserved for.
     */
    private void reserveTable(ArrayList<String> availableTables, LocalDateTime date) {

        String reservationID = UUID.randomUUID().toString();
        int[] tableChosen = chooseTable(availableTables);
        String sub = availableTables.get(tableChosen[0]);
        int capacity = Integer.parseInt(sub.substring(sub.indexOf(",") + 1, sub.length()));
        String dateFormatted = date.format(dateTimeFormat);
        String name = lastUsedAccount.getName();

        String csvLine = String.format("%d,%s,%d,%s,%d,%s",
                restaurantID, reservationID, capacity,
                dateFormatted, tableChosen[1], name);

        try (FileWriter fileWrite = new FileWriter(reservations, true)) {
            fileWrite.write("\n" + csvLine);
            System.out.println("Your reservation has been made!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** Checks for tables that are not reserved.
     *
     * @param date Date is used to check for tables that are reserved.
     * @return String arraylist containing information on non-reserved tables.
     */
    private ArrayList<String> checkAvailableTables (LocalDateTime date) {

        ArrayList<Integer> notAvailable = checkUnavailableTables(date);
        String line = "";
        String[] lineArray = new String[0];
        ArrayList<String> availableTables = new ArrayList<>();
        boolean unavailableTable;

        try(FileReader fileRead = new FileReader(tables);
            BufferedReader bufferRead = new BufferedReader(fileRead);) {
            bufferRead.readLine();
            while (bufferRead.ready()) {
                unavailableTable = false;
                line = bufferRead.readLine();
                lineArray = line.split(",");
                if (Integer.parseInt(lineArray[0]) == restaurantID) {
                    for (int table : notAvailable) {
                        if (Integer.parseInt(lineArray[1]) == table) {
                            unavailableTable = true;
                        }
                    }
                    if (!unavailableTable) {
                        availableTables.add(line.substring(line.indexOf(",") + 1, line.length()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availableTables;

    }

    /** Checks for unavailable tables.
     *
     * @param date Date is used to check for tables that are reserved.
     * @return Integer arraylist containing table numbers of unavailable tables.
     */
    private ArrayList<Integer> checkUnavailableTables (LocalDateTime date) {

        String[] line = new String[0];
        ArrayList<Integer> tablesNotAvailable = new ArrayList<>();

        try (FileReader fileRead = new FileReader(reservations);
             BufferedReader bufferRead = new BufferedReader(fileRead);) {
            bufferRead.readLine();
            while (bufferRead.ready()) {
                line = bufferRead.readLine().split(",");
                if (Integer.parseInt(line[0]) == restaurantID) {
                    if (isWithinRange(date, LocalDateTime.parse(line[3], dateTimeFormat))) {
                        tablesNotAvailable.add(Integer.parseInt(line[4]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablesNotAvailable;

    }

    /**
     * Prints out all current reservations for Yum! restaurant.
     */
    public void printReservations () {

        String line = "";
        String[] lineArray = new String[0];
        ArrayList<String> currentReservations = new ArrayList<>();
        System.out.println("--- Your reservations: ---");

        try (FileReader fileRead = new FileReader(reservations);
             BufferedReader bufferRead = new BufferedReader(fileRead);) {
            bufferRead.readLine();
            while (bufferRead.ready()) {
                line = bufferRead.readLine();
                lineArray = line.split(",");
                if (lineArray[5].equals(lastUsedAccount.getName())) {
                    currentReservations.add(line);
                }
            }
            for (String s : currentReservations) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Cancels all of the user's current reservations.
     * (NOT WORKING!!! Currently removes all reservations regardless of user, needs to be fixed
     * to only remove reservations made by the current user).
     */
    public void cancelReservations (String name) {
        ArrayList<String> lines = new ArrayList<String>();
        String temp = "";
        try {
            FileReader fr = new FileReader(reservations);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                temp = br.readLine();
                if (!temp.contains(name)) {
                    lines.add(temp);
                }
            }
            br.close();
            FileWriter fw1 = new FileWriter(reservations, false);
            fw1.write("");
            fw1.flush();
            fw1.close();
            FileWriter fw2 = new FileWriter(reservations, true);
            for (int i = 0; i < lines.size(); i++) {
                fw2.write(lines.get(i));
                fw2.write("\n");
            }
            fw2.flush();
            fw2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** Checks whether a date is within 2-hours of another reserved date.
     *
     * @param wantedDate The date the user wants to reserve for.
     * @param reservedDate The date currently reserved.
     * @return True or false.
     */
    public boolean isWithinRange(LocalDateTime wantedDate, LocalDateTime reservedDate) {
        LocalDateTime reservedDateMinusWindow = reservedDate.minusHours(2);
        LocalDateTime reservedDatePlusWindow = reservedDate.plusHours(2);
        return (wantedDate.isEqual(reservedDateMinusWindow) || wantedDate.isEqual(reservedDatePlusWindow))
                || (wantedDate.isBefore(reservedDatePlusWindow) && wantedDate.isAfter(reservedDateMinusWindow));

    }
}
