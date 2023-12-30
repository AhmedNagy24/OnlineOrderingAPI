package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CartDatabase;
import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.CompoundOrder;
import com.example.softassign2api.Models.OrderStatus;
import com.example.softassign2api.Models.ShoppingCart;
import com.example.softassign2api.Models.SimpleOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class CompoundOrderService extends OrderService {
    public CompoundOrderService(OrderDatabase orderDb, CartDatabase cartDb, CustomerDatabase customerDb) {
        super(orderDb, cartDb, customerDb);
    }

    @Override
    public String placeOrder(int id) {
        return null;
    }

    @Override
    public String shipOrder(int id) {
        return null;
    }

    @Override
    public String cancelOrder(int id) {
        return null;
    }

    @Override
    public Object getOrder(int id) {
        return null;
    }

    public String addCompoundOrder(Map<String, String> map){
        CompoundOrder compOrder = new CompoundOrder();
        Random random = new Random();
        int shippingFees = random.nextInt(100);
        shippingFees /= map.size();
        int id = orderDatabase.getLastID()+1;
        ArrayList<String> cartsToRemove = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            SimpleOrder simpleOrder = new SimpleOrder();
            ShoppingCart cart = cartDatabase.getCart(entry.getKey());
            if (cart == null){
                return "Error: "+entry.getKey()+"'s cart is empty";
            }
            simpleOrder.setCart(cart);
            simpleOrder.setCustomer(entry.getKey());
            simpleOrder.setId(id++);
            simpleOrder.setShipDate(new Date());
            simpleOrder.setStatus(OrderStatus.pending);
            simpleOrder.setShippingAddresses(entry.getValue());
            simpleOrder.setShippingFees(shippingFees);
            simpleOrder.setTotalProdPrice(cart.getTotalPrice());
            cartsToRemove.add(entry.getKey());
            compOrder.addOrder(simpleOrder);
        }
        for (String customer : cartsToRemove){
            cartDatabase.removeCart(customer);
        }
        compOrder.setId(id);
        compOrder.setShipDate(new Date());
        compOrder.setStatus(OrderStatus.pending);
        compOrder.setShippingFees(shippingFees);
        orderDatabase.addOrder(compOrder);
        return "Order added successfully with id: "+compOrder.getId();
    }
}
