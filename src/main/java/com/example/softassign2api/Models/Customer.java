package com.example.softassign2api.Models;

import lombok.Getter;

public class Customer {
    private String userName;
    private String password;
    private double balance;
    private Boolean isLogin;

    public Customer(String userName, String password, double balance, Boolean isLogin) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.isLogin = isLogin;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
