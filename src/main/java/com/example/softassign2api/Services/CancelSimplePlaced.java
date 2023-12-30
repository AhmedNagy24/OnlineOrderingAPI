package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.OrderStatus;

public class CancelSimplePlaced implements OrderAction{
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    public CancelSimplePlaced(OrderDatabase orderDatabase, CustomerDatabase customerDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(int id) {
        String customer = orderDatabase.getOrder(id).getCustomer();
        double orderPrice = orderDatabase.getOrder(id).getTotalProdPrice();
        customerDatabase.getCustomer(customer).setBalance(customerDatabase.getCustomer(customer).getBalance() + orderPrice);
        orderDatabase.getOrder(id).setStatus(OrderStatus.cancelled);
        return "Order "+id+" is cancelled";
    }
}
