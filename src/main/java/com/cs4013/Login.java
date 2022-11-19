package com.cs4013;
//Author - Ahmed Abdalla - 21316333

import java.io.*;
import java.util.Scanner;

public class Login {
   private File loginFile = new File("src/storage/Login.csv");
   private String name;
   private String password;
   private boolean loggedIn = false;
   private int level; //0 = guest, 1 = customer/user, 2 = staff, 3 = manager, 4 = owner

    public Login() {

    }

    public static void main(String[] args) {
        Login login = new Login();
        login.createAccount();
    }

   public void login() {
        loggedIn = false;
       enterUsernamePasswordPrompt();
       try {
           String line = "";
           BufferedReader br = new BufferedReader(new FileReader("src/storage/Login.csv"));

           while((line = br.readLine()) != null) {
               String[] logins = line.split(",");
               System.out.println("Username: " + logins[0] + " Password: " + logins[1]);
               if(name.equalsIgnoreCase(logins[0]) && password.equals(logins[1])) {
                    loggedIn = true;
                    break; //or could add "&& !loggedIn" to while loop
               }
               else {
                   System.out.println("Error, incorrect username of password");
                   loggedIn = false;
               }
           }
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (IOException e) {
           e.printStackTrace();
       }

   }

   public void createAccount() {
       enterUsernamePasswordPrompt();
       try {
           //FileOutputStream fos = new FileOutputStream(loginFile, true);
           FileWriter out = new FileWriter(loginFile, true);
           String line = "";
           BufferedReader br = new BufferedReader(new FileReader("src/storage/Login.csv"));
           boolean alreadyExists = false;

           while((line = br.readLine()) != null) {
               String[] logins = line.split(",");
               if(name.equalsIgnoreCase(logins[0])) {
                   System.out.println("Username already exists");
                   alreadyExists = true;
                   break;
               }
           }
           if(!alreadyExists) {
               out.append(System.getProperty("line.separator") + name + "," + password + ",1");
           }

           out.close();
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
   }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void enterUsernamePasswordPrompt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Username:");
        name = scan.next();
        System.out.println("Enter Password:");
        password = scan.next();
    }
}
