package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Database.NotificationDB.INotificationDatabase;
import com.example.softassign2api.Database.OrderDB.IOrderDatabase;
import com.example.softassign2api.Database.ShoppingCartDB.ICartDatabase;
import com.example.softassign2api.Models.Order.Order;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Order.ShoppingCart;
import com.example.softassign2api.Models.Order.SimpleOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
abstract public class OrderService {
    protected IOrderDatabase orderDatabase;
    protected ICartDatabase cartDatabase;
    protected ICustomerDatabase customerDatabase;
    protected ICategoryDatabase categoryDatabase;
    protected INotificationDatabase notificationDatabase;
    public OrderService(@Qualifier("inMemoryOrder") IOrderDatabase OrderDb, @Qualifier("inMemoryCart") ICartDatabase CartDb, @Qualifier("inMemoryCustomer") ICustomerDatabase CustomerDb, @Qualifier("inMemoryCategory") ICategoryDatabase CategoryDb, @Qualifier("inMemoryNotification") INotificationDatabase notificationDb) {
        orderDatabase = OrderDb;
        cartDatabase = CartDb;
        customerDatabase = CustomerDb;
        categoryDatabase = CategoryDb;
        notificationDatabase = notificationDb;
    }
    abstract public String placeOrder(int id);
    abstract public String shipOrder(int id);
    abstract public String cancelOrder(int id);
    abstract public Object getOrder(int id);
    protected static ArrayList<Object> getCartInfo(Order order) {
        ShoppingCart cart = ((SimpleOrder) order).getCart();
        ArrayList<Object> cartInfo = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("name", entry.getKey().getName());
            productInfo.put("vendor", entry.getKey().getVendor());
            productInfo.put("price", entry.getKey().getPrice());
            productInfo.put("quantity", entry.getValue());
            cartInfo.add(productInfo);
        }
        return cartInfo;
    }
}
