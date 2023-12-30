package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Models.Order.Order;
import com.example.softassign2api.Models.Order.OrderStatus;

import java.util.Date;

public class CancelSimpleShipped implements IOrderAction {
    private ICustomerDatabase customerDatabase;
    public CancelSimpleShipped(ICustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }
    @Override
    public String performAction(Order order) {
        String customer = order.getCustomer();
        double shippingPrice = order.getShippingFees();
        Date date = new Date();
        if (date.getTime() - order.getShipDate().getTime() > 60000) {
            return "Order: "+ order.getId() +" cannot be cancelled as it has already been shipped";
        }
        customerDatabase.increaseBalance(customer, shippingPrice);
        order.setStatus(OrderStatus.placed);
        return "The shipping for order: "+order.getId()+" is cancelled";
    }
}
