package com.cs4013;
//Author - Ahmed Abdalla - 21316333

import java.io.*;
import java.util.Scanner;

public class Account {
   private File loginFile;
   private String name;
   private String password;
   private boolean loggedIn = false;
   private int level; //0 = guest, 1 = customer/user, 2 = staff, 3 = manager, 4 = owner

    public Account() {
        loginFile = new File("src/storage/Login.csv");
    }

    public static void main(String[] args) {
        Account account = new Account();
        Account account2 = new Account();
        account.login();
        account2.createAccount();
        account.setLevel(account2, 4);
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
                    this.name = name;
                    this.password = password;
                    this.level = Integer.parseInt(logins[2]);
                    break; //or could add "&& !loggedIn" to while loop
               }
               else {
                   loggedIn = false;
               }
           }
           if(!loggedIn) {
               System.out.println("Error, incorrect username of password");
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
               this.name = name;
               this.password = password;
               level = 1;
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

    public void setLevel(Account account, int level) {
        //If owner(level 4)

        if(isLoggedIn() && level == 4) {

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

    public void editCSV(String editTerm, String newTerm) {
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
                pw.println(scanner.next()); //Copies all code from loginTemp to Login.
            }
            scanner.close();
            pw.flush();
            pw.close();
            newFile.delete();

        }
        catch (Exception e) {

        }
    }

    public void deleteAccount() {

    }
}
