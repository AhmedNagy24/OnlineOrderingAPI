package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.CompoundOrder;
import com.example.softassign2api.Models.Order;
import com.example.softassign2api.Models.OrderStatus;

import java.util.ArrayList;

public class CancelCompoundPlaced implements OrderAction{
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    public CancelCompoundPlaced(OrderDatabase orderDatabase, CustomerDatabase customerDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(int id) {
        Order order = orderDatabase.getOrder(id);
        if (order == null){
            return "Error: Order ID: "+id+" does not exist";
        }
        ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
        for (Order o : orders) {
            double orderPrice = o.getTotalProdPrice();
            String customer = o.getCustomer();
            customerDatabase.getCustomer(customer).setBalance(customerDatabase.getCustomer(customer).getBalance() + orderPrice);
            o.setStatus(OrderStatus.cancelled);
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order ID: "+id+" cancelled successfully";
    }
}
