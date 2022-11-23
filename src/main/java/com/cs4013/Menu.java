package com.cs4013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Menu  {

    private File menu = new File("src/storage/menus.csv");
    private Account account;
    private HubInterface mainHub;

    public Menu (Account account, HubInterface mainHub) {
        this.account = account;
        this.mainHub = mainHub;
    }

    public void printMenu () {

        String line = "";
        String lineRestaurant = "";
        String[] lineArray = new String[0];
        ArrayList<String> currentReservations = new ArrayList<>();

        try (FileReader fileRead = new FileReader(menu);
             BufferedReader bufferRead = new BufferedReader(fileRead);) {
            bufferRead.readLine();
            while (bufferRead.ready()) {
                line = bufferRead.readLine();
                lineRestaurant = line.substring(0, line.indexOf(","));
                if (Integer.parseInt(lineRestaurant) == (account.getRestaurantID())) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        mainHub.options(account);

    }
}
