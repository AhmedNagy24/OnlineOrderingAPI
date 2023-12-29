package com.example.softassign2api.Models;

import java.util.HashMap;
import java.util.Map;

public class CompoundOrder extends Order {
    private Map<String, Order> customerMap;
    public CompoundOrder(){
        customerMap = new HashMap<>();
    }
    public void addOrder(Order order){
        customerMap.put(order.getCustomer(), order);
    }

}
