package com.example.softassign2api.Models.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Models.Inventory.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    ICategoryDatabase database;
    private String id;
    private Map<Product, Integer> cart;
    private double totalPrice;
    public ShoppingCart(ICategoryDatabase db, String userName){
        cart = new HashMap<>();
        database = db;
        totalPrice = 0.0;
        id = userName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String addProduct(String name, String vendor, int amount){
        Product prodToAdd = database.searchProd(name, vendor);
        if (prodToAdd != null){
            if (database.canRemove(prodToAdd, amount)){
                if (cart.containsKey(prodToAdd)){
                    int oldValue = cart.get(prodToAdd);
                    cart.put(prodToAdd, amount+oldValue);
                    totalPrice = calcTotal();
                    return amount+" of "+name+" from "+vendor+" is added successfully to cart";
                }else {
                    cart.put(prodToAdd, amount);
                    totalPrice = calcTotal();
                    return amount+" of "+name+" from "+vendor+" is added successfully to cart";
                }
            }
        }
        return "Error: Product not found!";
    }
    public String removeProduct(String name, String vendor, int amount){
        Product prodToRemove = database.searchProd(name, vendor);
        String out;
        if (prodToRemove != null && cart.containsKey(prodToRemove)){
            if (cart.get(prodToRemove) > amount){
                int oldValue = cart.get(prodToRemove);
                cart.put(prodToRemove, oldValue-amount);
                totalPrice = calcTotal();
                out = amount+" Of "+name+" From "+vendor+" is successfully removed from cart";
                return out;
            } else if (cart.get(prodToRemove) == amount) {
                cart.remove(prodToRemove);
                totalPrice = calcTotal();
                out = name+" From "+vendor+" is successfully removed from cart";
                return out;
            }else {
                out = "Error: The cart have less than "+amount+" of "+name+" From "+vendor;
                return out;
            }
        }
        return "Error: Product not found!";
    }
    private double calcTotal(){
        double total = 0;
        for (Map.Entry<Product, Integer> element : cart.entrySet()){
            total += (element.getKey().getPrice()*element.getValue());
        }
        return total;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
