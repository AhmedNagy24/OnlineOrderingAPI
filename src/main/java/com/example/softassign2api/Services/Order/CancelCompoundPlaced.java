package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Order.*;

import java.util.ArrayList;

public class CancelCompoundPlaced implements IOrderAction {
    private ICustomerDatabase customerDatabase;
    private ICategoryDatabase categoryDatabase;
    public CancelCompoundPlaced(ICustomerDatabase customerDatabase, ICategoryDatabase categoryDb) {
        this.customerDatabase = customerDatabase;
        this.categoryDatabase = categoryDb;
    }
    @Override
    public String performAction(Order order) {
        ArrayList<Order> orders = ((CompoundOrder) order).getOrders();
        for (Order o : orders) {
            double orderPrice = o.getTotalProdPrice();
            String customer = o.getCustomer();
            customerDatabase.increaseBalance(customer, orderPrice);
            ShoppingCart cart = ((SimpleOrder) o).getCart();
            for (Product p : cart.getCart().keySet()) {
                categoryDatabase.incPartsNum(p, cart.getCart().get(p));
            }
            o.setStatus(OrderStatus.cancelled);
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order ID: "+order.getId()+" cancelled successfully";
    }
}
