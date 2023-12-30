package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Order.Order;
import com.example.softassign2api.Models.Order.OrderStatus;
import com.example.softassign2api.Models.Order.ShoppingCart;
import com.example.softassign2api.Models.Order.SimpleOrder;

import java.util.Map;

public class CancelSimplePlaced implements IOrderAction {
    private final ICustomerDatabase customerDatabase;
    private final ICategoryDatabase categoryDatabase;

    public CancelSimplePlaced(ICustomerDatabase customerDatabase, ICategoryDatabase categoryDb) {
        this.customerDatabase = customerDatabase;
        this.categoryDatabase = categoryDb;
    }

    @Override
    public String performAction(Order order) {
        String customer = order.getCustomer();
        double orderPrice = order.getTotalProdPrice();
        customerDatabase.increaseBalance(customer, orderPrice);
        ShoppingCart cart = ((SimpleOrder) order).getCart();
        for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
            categoryDatabase.incPartsNum(entry.getKey(), entry.getValue());
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order: " + order.getId() + " is cancelled";
    }
}
