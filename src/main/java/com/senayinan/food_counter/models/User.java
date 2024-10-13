package com.senayinan.food_counter.models;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String Id;
    private String userName;
    private String password;
    //User's meals are organized by day
    private List<Day>days = new ArrayList<>();

    public User(String id, String userName, String password, List<Day> days) {
        Id = id;
        this.userName = userName;
        this.password = password;
        this.days = days;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }



}

