package com.cs4013;

//creates guest object
public class Guests {
    private String name;
    private String date;
    private int phoneNum;
    private int guestNum;
    private int restId;
    private String time;
    private int tableId;

    Guests(String name, String date, int phoneNum, int guestNum, int restId, String time, int tableId) {
        this.name = name;
        this.date = date;
        this.phoneNum = phoneNum;
        this.guestNum = guestNum;
        this.restId = restId;
        this.time = time;
        this.tableId = tableId;
        
    }

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public int getTableId() {
        return tableId;
    }
    public int getPhoneNum() {
        return phoneNum;
    }
    public int getGuestNum() {
        return guestNum;
    }
    public int getRestId() {
        return restId;
    }
    public String guestsToString(){
        return (getRestId()+ ","+ getTableId() + ","+ getName()+ ","+getPhoneNum()+"," + getGuestNum() + "," + getDate() + "," + getTime());
    }
}
