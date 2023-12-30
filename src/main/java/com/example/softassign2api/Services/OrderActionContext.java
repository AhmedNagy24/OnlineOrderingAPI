package com.example.softassign2api.Services;

public class OrderActionContext {
    private OrderAction action;

    public OrderActionContext(OrderAction action) {
        this.action = action;
    }

    public String executeAction(int id) {
        return action.performAction(id);
    }

}
