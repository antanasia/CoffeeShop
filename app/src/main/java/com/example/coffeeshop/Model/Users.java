package com.example.coffeeshop.Model;

public class Users {
    private String username, number, password;

    public Users() {

    }

    public Users(String username, String number, String password) {
        this.username = username;
        this.number = number;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
