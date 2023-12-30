package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CategoryDatabase;
import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.*;

import java.util.Map;

public class CancelSimplePlaced implements OrderAction{
    private CustomerDatabase customerDatabase;
    private CategoryDatabase categoryDatabase;
    public CancelSimplePlaced(CustomerDatabase customerDatabase, CategoryDatabase categoryDb) {
        this.customerDatabase = customerDatabase;
        this.categoryDatabase = categoryDb;
    }
    @Override
    public String performAction(Order order) {
        String customer = order.getCustomer();
        double orderPrice = order.getTotalProdPrice();
        customerDatabase.increaseBalance(customer, orderPrice);
        ShoppingCart cart = ((SimpleOrder)order).getCart();
        for(Map.Entry<Product, Integer> entry : cart.getCart().entrySet()){
            categoryDatabase.incPartsNum(entry.getKey(), entry.getValue());
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order: "+order.getId()+" is cancelled";
    }
}
