package com.example.softassign2api.Models;

public class SimpleOrder extends Order {
    private ShoppingCart cart;
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    public ShoppingCart getCart() {
        return cart;
    }
}
