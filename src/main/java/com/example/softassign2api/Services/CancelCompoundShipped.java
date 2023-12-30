package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.CompoundOrder;
import com.example.softassign2api.Models.Order;
import com.example.softassign2api.Models.OrderStatus;

import java.util.ArrayList;
import java.util.Date;

public class CancelCompoundShipped implements OrderAction{
    private CustomerDatabase customerDatabase;
    public CancelCompoundShipped(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(Order order) {
        if(new Date().getTime() - order.getShipDate().getTime() > 60000){
            return "Order: "+ order.getId() +" cannot be cancelled as it has already been shipped";
        }
        ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
        for (Order tempOrd : orders) {
            String customer = tempOrd.getCustomer();
            double shippingPrice = tempOrd.getShippingFees();
            customerDatabase.increaseBalance(customer, shippingPrice);
            tempOrd.setStatus(OrderStatus.placed);
        }
        order.setStatus(OrderStatus.placed);
        return "The shipping for order: "+order.getId()+" is cancelled";
    }
}
