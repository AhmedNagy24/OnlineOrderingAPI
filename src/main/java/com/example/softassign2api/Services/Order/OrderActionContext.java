package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Models.Order.Order;

public class OrderActionContext {
    private final IOrderAction action;

    public OrderActionContext(IOrderAction action) {
        this.action = action;
    }

    public String executeAction(Order order) {
        return action.performAction(order);
    }

}
