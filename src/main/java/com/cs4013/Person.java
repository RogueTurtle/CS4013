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

    public String getName() {
        return name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

}
