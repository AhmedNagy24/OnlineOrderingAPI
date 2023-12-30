package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Order;
import org.springframework.stereotype.Component;

public interface OrderDatabase {
    boolean addOrder(Order order);
    boolean removeOrder(int id);
    Order getOrder(int id);
    int getLastID();
}
