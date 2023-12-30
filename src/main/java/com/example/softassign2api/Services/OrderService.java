package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CartDatabase;
import com.example.softassign2api.Database.CategoryDatabase;
import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import com.example.softassign2api.Models.Order;
import com.example.softassign2api.Models.Product;
import com.example.softassign2api.Models.ShoppingCart;
import com.example.softassign2api.Models.SimpleOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
abstract public class OrderService {
    protected OrderDatabase orderDatabase;
    protected CartDatabase cartDatabase;
    protected CustomerDatabase customerDatabase;
    protected CategoryDatabase categoryDatabase;
    public OrderService(@Qualifier("inMemoryOrder") OrderDatabase OrderDb,@Qualifier("inMemoryCart") CartDatabase CartDb, @Qualifier("inMemoryCustomer") CustomerDatabase CustomerDb, @Qualifier("inMemoryCategory") CategoryDatabase CategoryDb) {
        orderDatabase = OrderDb;
        cartDatabase = CartDb;
        customerDatabase = CustomerDb;
        categoryDatabase = CategoryDb;
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
