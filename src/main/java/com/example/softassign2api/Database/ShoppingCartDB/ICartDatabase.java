package com.example.softassign2api.Database.ShoppingCartDB;

import com.example.softassign2api.Models.Order.ShoppingCart;

public interface ICartDatabase {
    String addToCart(String id, String name, String vendor, int amount);

    String delFromCart(String id, String name, String vendor, int amount);

    boolean removeCart(String id);

    ShoppingCart getCart(String id);
}
