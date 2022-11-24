package com.cs4013;

/**
 * Made by Ahmed Abdalla
 * Student: 21316333
 */


public class Person {

    private String name;
    private int phoneNum;

    public Person() {
        
    }

    public Person(String name, int phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return int
     */
    public int getPhoneNum() {
        return phoneNum;
    }

}
