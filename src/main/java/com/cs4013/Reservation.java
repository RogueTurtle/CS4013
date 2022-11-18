package com.cs4013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Reservation {
    File resFile = new File("src/storage/Reservations.csv");
    File tableFile = new File("src/storage/Tables.csv");

    ArrayList<Guests> guestList = new ArrayList<Guests>();
    public void addReservation(String name, String date, int phoneNum, int guestNum, int restId, String time, int tableId) {
        Guests guests = new Guests(name, date, phoneNum, guestNum, restId,time,tableId);
        guestList.add(guests);
        String[] guestString = guests.guestsToString().split(",");
        try {
            FileWriter out = new FileWriter(resFile, true);
            for (int i = 0; i < guestString.length; i++) {
                out.write(guestString[i]);
                out.write(",");
            }
            out.write("\n");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void cancelation(int phoneNum) {
        ArrayList<String> lines = new ArrayList<String>();
        String temp = "";
        String phoneString = Integer.toString(phoneNum);
        try {
            FileReader fr = new FileReader(resFile);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                temp = br.readLine();
                if (!temp.contains(phoneString)) {
                    lines.add(temp);
                }
            }
            br.close();
            FileWriter fw1 = new FileWriter(resFile, false);
            fw1.write("");
            fw1.flush();
            fw1.close();
            FileWriter fw2 = new FileWriter(resFile, true);
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

    public String tables(int restId, String time, String date) {
        String line = "";
        ArrayList<String> tableLines = new ArrayList<String>();
        ArrayList<String> resLines = new ArrayList<String>();
        String tablesString = "";
        try {
            FileReader tableFr = new FileReader(tableFile);
            BufferedReader tableBr = new BufferedReader(tableFr);
            while (tableBr.ready()) {
                line = tableBr.readLine();
                if (line.charAt(0) == restId) {
                    tableLines.add(line);
                }
            }
            tableBr.close();
            
            FileReader resFr = new FileReader(resFile);
            BufferedReader resBr = new BufferedReader(resFr);
            while (resBr.ready()) {
                line = resBr.readLine();
                if (line.charAt(0) == restId) {
                    resLines.add(line);
                }
            }
            resBr.close();

            String dateString = "";
            String timeString = "";
            ArrayList<String> freeTablesID = new ArrayList<String>();

            for (int i = 0; i < resLines.size() ; i++) {
                String[] comp = resLines.get(i).split(",");
                dateString = comp[5];
                timeString = comp[6];
                if (dateString != date || timeString != time) {
                    freeTablesID.add(comp[1]);
                }
            }

            ArrayList<String> freeTables = new ArrayList<String>();
            for (int j = 0; j < freeTablesID.size(); j++) {
                
            
            for (int i = 0; i < tableLines.size(); i++) {
                String tableChar = Character.toString(tableLines.get(i).charAt(2));
                if (tableChar == freeTablesID.get(j)) {
                    freeTables.add(tableLines.get(i));
                }
            }}

            
        for (int i = 0; i < freeTables.size(); i++) {
            tablesString += freeTables.get(i);
            tablesString += ",\n";
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return tablesString;
    }
}