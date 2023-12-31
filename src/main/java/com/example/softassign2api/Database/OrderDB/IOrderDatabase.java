package com.example.softassign2api.Database.OrderDB;

import com.example.softassign2api.Models.Order.Order;

public interface IOrderDatabase {
    boolean addOrder(Order order);

    Order getOrder(int id);

    int getLastID();
}
