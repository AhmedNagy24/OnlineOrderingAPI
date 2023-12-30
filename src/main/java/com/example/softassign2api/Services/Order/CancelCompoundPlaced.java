package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Database.NotificationDB.INotificationDatabase;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Notification.CancelNotification;
import com.example.softassign2api.Models.Notification.NotificationTemplate;
import com.example.softassign2api.Models.Order.*;

import java.util.ArrayList;

public class CancelCompoundPlaced implements IOrderAction {
    private final ICustomerDatabase customerDatabase;
    private final ICategoryDatabase categoryDatabase;
    private final INotificationDatabase notificationDatabase;

    public CancelCompoundPlaced(ICustomerDatabase customerDatabase, ICategoryDatabase categoryDb, INotificationDatabase notificationDatabase) {
        this.customerDatabase = customerDatabase;
        this.categoryDatabase = categoryDb;
        this.notificationDatabase = notificationDatabase;
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
            NotificationTemplate template = new CancelNotification();
            notificationDatabase.saveNotification(customerDatabase.getCustomer(customer), template);
        }
        order.setStatus(OrderStatus.cancelled);
        return "Order ID: " + order.getId() + " cancelled successfully";
    }
}
