package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;

public class CancelCompoundShipped implements OrderAction{
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    public CancelCompoundShipped(OrderDatabase orderDatabase, CustomerDatabase customerDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(int id) {
        return null;
    }
}
