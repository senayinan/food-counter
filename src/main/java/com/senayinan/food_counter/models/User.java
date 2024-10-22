package com.senayinan.food_counter.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractEntity{
    @NotNull
    private String userName;
    @NotNull
    private String pwHash;
    @Email
    private String email;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Day>days = new ArrayList<>();

    public User(String userName, String password, String email, List<Day> days) {
        this.userName = userName;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.days = days;
    }

    public User() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
    public boolean isMatchingPassword(String password) {

        return encoder.matches(password, pwHash);
    }


    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", days=" + days +
                '}';
    }


}

