package com.cs4013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Reservation {
    File resFile = new File("src/storage/Reservations.csv");

    public static void main(String[] args) {
        Reservation res = new Reservation();
        res.guests("john", "today",12345,5,1,"now",1);
        res.cancelation(12345);
    }

    ArrayList<Guests> guestList = new ArrayList<Guests>();
    public void guests(String name, String date, int phoneNum, int guestNum, int restId, String time, int tableId) {
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

    public void tables(int restId, String time, String date) {
        
    }
}