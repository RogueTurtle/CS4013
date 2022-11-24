package com.cs4013;
//Author - Ahmed Abdalla - 21316333

import java.io.*;
import java.util.Scanner;

public class Account {
   private File loginFile;
   private String name;
   private String password;
   private boolean loggedIn = false;
   private int level; //0 = guest, 1 = customer/user, 2 = waiter, 3 = frontStaff, 4 = chef, 5. manager, 6. owner

    /**
     * Creates account object thats information is Null at first
     */
    public Account() {
        loginFile = new File("src/storage/Login.csv");
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Account account = new Account();
        Account account2 = new Account();
        account.login();
        account2.createAccount();
        //account.setLevel(account2, 4);
        account.deleteAccount("xd");
    }

    /**
     * Allows user to log in using prompts
     */

   public void login() {
        loggedIn = false;
       System.out.println("LOG-IN\n" + "------------------");
        enterUsernamePasswordPrompt();
        try {
           String line = "";
           BufferedReader br = new BufferedReader(new FileReader("src/storage/Login.csv"));

           while((line = br.readLine()) != null) {
               String[] logins = line.split(",");
               if(name.equalsIgnoreCase(logins[0]) && password.equals(logins[1])) {
                    loggedIn = true;
                    this.name = name;
                    this.password = password;
                    this.level = Integer.parseInt(logins[2]);
                   System.out.println("Login Successful!");
                    break; //or could add "&& !loggedIn" to while loop
               }
               else {
                   loggedIn = false;
               }
           }
           if(!loggedIn) {
               System.out.println("Error: incorrect username or password");
           }
           br.close();
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (IOException e) {
           e.printStackTrace();
       }

   }

    /**
     * Allows user to create account using prompts
     */

   public void createAccount() {
       System.out.println("SIGN-UP\n" + "------------------");
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
               this.name = name;
               this.password = password;
               level = 1;
               System.out.println("Account created! Please log-in.");
           }
           br.close();
           out.close();
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
   }

    
    /** 
     * @return boolean
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    private void enterUsernamePasswordPrompt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Username:");
        name = scan.next();
        System.out.println("Enter Password(Case-Sensitive):");
        password = scan.next();
    }

    /**
     * Allows admin user to setLevel of another account
     * @param account The account you want to modify
     * @param level The level you want to set the other account too(1-6)
     *              Level 0: guest
     *              Level 1: customer/user
     *              Level 2: waiter
     *              Level 3  frontStaff
     *              Level 4: chef
     *              Level 5: manager
     *              Level 6: owner
     */

    public void setLevel(Account account, int level) {
        //If owner(level 6)

        if(isLoggedIn() && level == 6) {

            try {
                String line = "";
                BufferedReader br = new BufferedReader(new FileReader("src/storage/Login.csv"));

                while((line = br.readLine()) != null) {
                    String[] logins = line.split(",");
                    if(account.name.equalsIgnoreCase(logins[0]) && Integer.toString(account.level).equals(logins[2])) {
                        account.level = level;
                        editCSV(logins[2], Integer.toString(level));
                        break;
                    }
                }
                br.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Error, you do not have permission to promote/demote accounts");
        }
    }

    
    /** 
     * @param editTerm
     * @param newTerm
     */
    private void editCSV(String editTerm, String newTerm) {
        String tempFilePath = "src/storage/loginTemp.csv";
        File newFile = new File(tempFilePath);
        File oldFile = loginFile;

        String name;
        String password;
        String level;

        try {
            FileWriter fileWriter = new FileWriter(tempFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            Scanner scan = new Scanner(new File("src/storage/Login.csv"));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext()) {
                name = scan.next();
                password = scan.next();
                level = scan.next();

            if(level.equals(editTerm)) {
                    printWriter.print(name + "," + password + "," + newTerm + "\n");
                }
               else {
                    printWriter.print(name + "," + password + "," + level + "\n");
                }
            }
            scan.close();
            printWriter.flush();
            printWriter.close();
            bufferedWriter.close();
            //oldFile.delete() not working possibly due to intelliJ not having permission to use .delete. I closed all writers/readers so thats not an issue.
            //Hence the following code. However newFile.delete() works because its not factored.
            Scanner scanner = new Scanner(new File(tempFilePath));
            FileWriter fw = new FileWriter(loginFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            while(scanner.hasNext()) {
                pw.print(scanner.next() + "\n"); //Copies all code from loginTemp to Login.
            }
            scanner.close();
            pw.flush();
            pw.close();
            newFile.delete();

        }
        catch (Exception e) {

        }
    }

    
    /** 
     * @param username
     */
    public void deleteAccount(String username) {
        if (level == 6 || name.equalsIgnoreCase(username)) {
            deleteCSV(username);
        }
    }

    
    /** 
     * @param username
     */
    private void deleteCSV(String username) {
        String tempFilePath = "src/storage/loginTemp.csv";
        File newFile = new File(tempFilePath);
        File oldFile = loginFile;

        String name;
        String password;
        String level;

        try {
            FileWriter fileWriter = new FileWriter(tempFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            Scanner scan = new Scanner(new File("src/storage/Login.csv"));
            scan.useDelimiter("[,\n]");

            while (scan.hasNext()) {
                name = scan.next();
                password = scan.next();
                level = scan.next();

                if (name.equals(username)) {

                } else {
                    printWriter.print(name + "," + password + "," + level + "\n");
                }
            }
            scan.close();
            printWriter.flush();
            printWriter.close();
            bufferedWriter.close();
            //oldFile.delete() not working possibly due to intelliJ not having permission to use .delete. I closed all writers/readers so thats not an issue.
            //Hence the following code. However newFile.delete() works because its not factored.
            Scanner scanner = new Scanner(new File(tempFilePath));
            FileWriter fw = new FileWriter(loginFile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            while (scanner.hasNext()) {
                pw.println(scanner.next()); //Copies all code from loginTemp to Login.
            }
            scanner.close();
            pw.flush();
            pw.close();
            newFile.delete();
        } catch (Exception e) {

        }
    }

    
    /** 
     * @return int
     */
    public int getLevel() {
        return level;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Username: " + name;
    }
}
