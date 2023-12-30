package com.example.softassign2api.Services;

import com.example.softassign2api.Models.Order;

public class OrderActionContext {
    private OrderAction action;

    public OrderActionContext(OrderAction action) {
        this.action = action;
    }

    public String executeAction(Order order) {
        return action.performAction(order);
    }

}
