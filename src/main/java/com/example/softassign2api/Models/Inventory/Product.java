package com.example.softassign2api.Models.Inventory;

import java.util.Random;

public class Product {
    private String serialNum;
    private String name;
    private String vendor;
    private double price;

    public Product(String name, String vendor, int price) {
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        int random = new Random().nextInt(9999);
        this.serialNum = name + vendor + random;
    }


    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
