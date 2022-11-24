package com.cs4013;

/*
 * creates new guest object for use in reservations
 */
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

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /** 
     * @return String
     */
    public String getDate() {
        return date;
    }
    
    /** 
     * @return String
     */
    public String getTime() {
        return time;
    }
    
    /** 
     * @return int
     */
    public int getTableId() {
        return tableId;
    }
    
    /** 
     * @return int
     */
    public int getPhoneNum() {
        return phoneNum;
    }
    
    /** 
     * @return int
     */
    public int getGuestNum() {
        return guestNum;
    }
    
    /** 
     * @return int
     */
    public int getRestId() {
        return restId;
    }
    
    /** 
     * @return String
     */
    public String guestsToString(){
        return (getRestId()+ ","+ getTableId() + ","+ getName()+ ","+getPhoneNum()+"," + getGuestNum() + "," + getDate() + "," + getTime());
    }
}
