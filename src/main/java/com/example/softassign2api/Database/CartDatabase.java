package com.example.softassign2api.Database;

import com.example.softassign2api.Models.ShoppingCart;

public interface CartDatabase {
    String addToCart(String id, String name, String vendor, int amount);
    String delFromCart(String id, String name, String vendor, int amount);
    boolean removeCart(String id);
    ShoppingCart getCart(String id);
}
