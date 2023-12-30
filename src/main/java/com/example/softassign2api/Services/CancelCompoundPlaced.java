package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CategoryDatabase;
import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.*;

import java.util.ArrayList;

public class CancelCompoundPlaced implements OrderAction{
    private CustomerDatabase customerDatabase;
    private CategoryDatabase categoryDatabase;
    public CancelCompoundPlaced(CustomerDatabase customerDatabase, CategoryDatabase categoryDb) {
        this.customerDatabase = customerDatabase;
        this.categoryDatabase = categoryDb;
    }
    @Override
    public String performAction(Order order) {
        ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
        for (Order o : orders) {
            double orderPrice = o.getTotalProdPrice();
            String customer = o.getCustomer();
            customerDatabase.increaseBalance(customer, orderPrice);
            ShoppingCart cart = ((SimpleOrder) o).getCart();
            for (Product p : cart.getCart().keySet()) {
                categoryDatabase.incPartsNum(p, cart.getCart().get(p));
            }
            o.setStatus(OrderStatus.cancelled);
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order ID: "+order.getId()+" cancelled successfully";
    }
}
