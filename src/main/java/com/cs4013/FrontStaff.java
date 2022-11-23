package com.cs4013;

public class FrontStaff extends Staff{
    FrontStaff(String name, int phoneNum){
        super(name, phoneNum);
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public int getPhoneNum() {
        return super.getPhoneNum();
    }
}
