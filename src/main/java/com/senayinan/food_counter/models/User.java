package com.senayinan.food_counter.models;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{
    @NotNull
    private String userName;
    @NotNull
    private String pwHash;
    @Email
    private String email;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(String userName, String password) {
        this.userName = userName;
        this.pwHash = encoder.encode(password);
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.pwHash = encoder.encode(password);
    }

    public User() {}
    public void setPassword(String password) {
        this.pwHash = encoder.encode(password);
    }


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

    public boolean isMatchingPassword(String password) {


        return encoder.matches(password, pwHash);
    }


    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", days=" +
                '}';
    }


}

