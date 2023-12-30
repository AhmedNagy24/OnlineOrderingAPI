package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CartDatabase;
import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.OrderDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
abstract public class OrderService {
    protected OrderDatabase orderDatabase;
    protected CartDatabase cartDatabase;
    protected CustomerDatabase customerDatabase;
    public OrderService(@Qualifier("inMemoryOrder") OrderDatabase OrderDb,@Qualifier("inMemoryCart") CartDatabase CartDb, @Qualifier("inMemoryCustomer") CustomerDatabase CustomerDb) {
        orderDatabase = OrderDb;
        cartDatabase = CartDb;
        customerDatabase = CustomerDb;
    }
    abstract public String placeOrder(int id);
    abstract public String shipOrder(int id);
    abstract public String cancelOrder(int id);
    abstract public Object getOrder(int id);
}
