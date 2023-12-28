package com.example.softassign2api.Models;

import com.example.softassign2api.Database.CategoryDatabase;
import com.example.softassign2api.Database.InMemoryCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    CategoryDatabase database;
    private int id;
    private Map<Product, Integer> cart;

    public double getTotalPrice() {
        return totalPrice;
    }

    private double totalPrice;
    ShoppingCart(CategoryDatabase db){
        cart = new HashMap<>();
        database = db;
        totalPrice = 0.0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean addProduct(String name, String vendor, int amount){
        Product prodToAdd = database.searchProd(name, vendor);
        if (prodToAdd != null){
            if (database.decPartsNum(prodToAdd, amount)){
                int oldValue = cart.get(prodToAdd);
                cart.put(prodToAdd, amount+oldValue);
                totalPrice = calcTotal();
                return true;
            }
        }
        return false;
    }
    public boolean removeProduct(String name, String vendor, int amount){
        Product prodToRemove = database.searchProd(name, vendor);
        if (prodToRemove != null && cart.containsKey(prodToRemove)){
            if (cart.get(prodToRemove) > amount){
                int oldValue = cart.get(prodToRemove);
                cart.put(prodToRemove, oldValue-amount);
                database.incPartsNum(prodToRemove, amount);
                totalPrice = calcTotal();
                return true;
            } else if (cart.get(prodToRemove) == amount) {
                cart.remove(prodToRemove);
                database.incPartsNum(prodToRemove, amount);
                totalPrice = calcTotal();
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
    private double calcTotal(){
        double total = 0;
        for (Map.Entry<Product, Integer> element : cart.entrySet()){
            total += (element.getKey().getPrice()*element.getValue());
        }
        return total;
    }
}
