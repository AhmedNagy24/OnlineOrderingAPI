package com.example.softassign2api.Models;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    private ArrayList<Order> orders;
    public CompoundOrder(){
        orders = new ArrayList<>();
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }
    @Override
    public double getTotalProdPrice(){
        double total = 0;
        for(Order order : orders){
            total += order.getTotalProdPrice();
        }
        return total;
    }
}
