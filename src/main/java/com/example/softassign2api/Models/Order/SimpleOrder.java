package com.example.softassign2api.Models.Order;

public class SimpleOrder extends Order {
    private ShoppingCart cart;

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public double getTotalProdPrice() {
        return cart.getTotalPrice();
    }
}
