package com.example.softassign2api.Database.OrderDB;

import com.example.softassign2api.Models.Order.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InMemoryOrder implements IOrderDatabase {
    private static final ArrayList<Order> orders = new ArrayList<>();

    @Override
    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    public Order getOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public int getLastID() {
        if (orders.isEmpty()) {
            return 0;
        } else {
            return orders.get(orders.size() - 1).getId();
        }
    }
}
