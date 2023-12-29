package com.example.softassign2api.Models;

import com.example.softassign2api.Database.CartDatabase;

public class SimpleOrder extends Order {
    private ShoppingCart cart;
    private CartDatabase cartDatabase;
    public SimpleOrder(CartDatabase db){
        cartDatabase = db;
        cart = cartDatabase.getCart(customer);
    }
    public ShoppingCart getCart() {
        return cart;
    }
}
