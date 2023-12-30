package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.OrderStatus;

import java.util.Date;

public class CancelSimpleShipped implements OrderAction{
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    public CancelSimpleShipped(OrderDatabase orderDatabase, CustomerDatabase customerDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(int id) {
        String customer = orderDatabase.getOrder(id).getCustomer();
        double shippingPrice = orderDatabase.getOrder(id).getShippingFees();
        Date date = new Date();
        if (date.getTime() - orderDatabase.getOrder(id).getShipDate().getTime() > 60000) {
            return "Order "+ id +" cannot be cancelled as it has already been shipped";
        }
        customerDatabase.getCustomer(customer).setBalance(customerDatabase.getCustomer(customer).getBalance() + shippingPrice);
        orderDatabase.getOrder(id).setStatus(OrderStatus.placed);
        return "Order " + id + " is cancelled";
    }
}
