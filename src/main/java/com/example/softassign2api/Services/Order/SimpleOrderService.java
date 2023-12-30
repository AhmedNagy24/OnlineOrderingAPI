package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CategoryDB.InMemoryCategory;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Database.CustomerDB.InMemoryCustomer;
import com.example.softassign2api.Database.NotificationDB.INotificationDatabase;
import com.example.softassign2api.Database.NotificationDB.InMemoryNotification;
import com.example.softassign2api.Database.OrderDB.IOrderDatabase;
import com.example.softassign2api.Database.ShoppingCartDB.ICartDatabase;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Notification.NotificationTemplate;
import com.example.softassign2api.Models.Notification.PlacedNotification;
import com.example.softassign2api.Models.Notification.ShippedNotification;
import com.example.softassign2api.Models.Order.Order;
import com.example.softassign2api.Models.Order.OrderStatus;
import com.example.softassign2api.Models.Order.ShoppingCart;
import com.example.softassign2api.Models.Order.SimpleOrder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleOrderService extends OrderService {
    public SimpleOrderService(IOrderDatabase orderDb, ICartDatabase cartDb, ICustomerDatabase customerDb, ICategoryDatabase categoryDb, INotificationDatabase notificationDb) {
        super(orderDb, cartDb, customerDb, categoryDb, notificationDb);
    }

    @Override
    public String placeOrder(int id) {
        if (orderDatabase.getOrder(id) == null) {
            return "Error: Order ID: " + id + " does not exist";
        }
        if (orderDatabase.getOrder(id).getStatus() != OrderStatus.pending) {
            return "Error: Order ID: " + id + " cannot be placed";
        }
        String customer = orderDatabase.getOrder(id).getCustomer();
        double totalPrice = orderDatabase.getOrder(id).getTotalProdPrice();
        ShoppingCart cart = ((SimpleOrder) orderDatabase.getOrder(id)).getCart();
        if (!customerDatabase.canDecreaseBalance(customer, totalPrice)) {
            return "Error: Not enough balance to place order\n Total price: " + totalPrice;
        }
        for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
            if (!categoryDatabase.canRemove(entry.getKey(), entry.getValue())) {
                return "Error: Not enough parts in stock for " + entry.getKey().getName() + " from " + entry.getKey().getVendor();
            }
        }
        for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
            categoryDatabase.decPartsNum(entry.getKey(), entry.getValue());
        }
        customerDatabase.decreaseBalance(customer, totalPrice);
        orderDatabase.getOrder(id).setStatus(OrderStatus.placed);
        NotificationTemplate template = new PlacedNotification();
        notificationDatabase.saveNotification(customerDatabase.getCustomer(customer), template);
        return "Order ID: " + id + " placed successfully";
    }

    @Override
    public String shipOrder(int id) {
        if (orderDatabase.getOrder(id) == null) {
            return "Error: Order ID: " + id + " does not exist";
        }
        if (orderDatabase.getOrder(id).getStatus() != OrderStatus.placed) {
            return "Error: Order ID: " + id + " cannot be shipped";
        }
        String customer = orderDatabase.getOrder(id).getCustomer();
        double shippingFees = orderDatabase.getOrder(id).getShippingFees();
        if (customerDatabase.decreaseBalance(customer, shippingFees)) {
            orderDatabase.getOrder(id).setStatus(OrderStatus.shipped);
            orderDatabase.getOrder(id).setShipDate(new Date());
            NotificationTemplate template = new ShippedNotification();
            notificationDatabase.saveNotification(customerDatabase.getCustomer(customer), template);
            return "Order ID: " + id + " shipped successfully";
        }
        return "Error: Not enough balance to ship order\n Shipping fees: " + shippingFees;
    }

    @Override
    public String cancelOrder(int id) {
        Order order = orderDatabase.getOrder(id);
        if (order == null) {
            return "Error: Order ID: " + id + " does not exist";
        }
        if (order.getStatus() == OrderStatus.placed) {
            OrderActionContext context = new OrderActionContext(new CancelSimplePlaced(new InMemoryCustomer(), new InMemoryCategory(), new InMemoryNotification()));
            return context.executeAction(orderDatabase.getOrder(id));
        } else if (order.getStatus() == OrderStatus.shipped) {
            OrderActionContext context = new OrderActionContext(new CancelSimpleShipped(new InMemoryCustomer()));
            return context.executeAction(orderDatabase.getOrder(id));
        } else {
            return "Error: Order ID: " + id + " cannot be cancelled";
        }
    }

    @Override
    public Object getOrder(int id) {
        Order temp = orderDatabase.getOrder(id);
        if (temp == null) {
            return "Error: Order ID: " + id + " does not exist";
        }
        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("id", temp.getId());
        orderInfo.put("customer", temp.getCustomer());
        orderInfo.put("status", temp.getStatus());
        orderInfo.put("shippingFees", temp.getShippingFees());
        orderInfo.put("totalProdPrice", temp.getTotalProdPrice());
        orderInfo.put("shipDate", temp.getShipDate());
        orderInfo.put("shippingAddresses", temp.getShippingAddresses());
        ArrayList<Object> cartInfo = getCartInfo(temp);
        orderInfo.put("cart", cartInfo);
        return orderInfo;
    }

    public String addOrder(String customer, String shippingAddress) {
        SimpleOrder order = new SimpleOrder();
        ShoppingCart cart = cartDatabase.getCart(customer);
        if (cart == null) {
            return "Cart is empty or user does not exist";
        }
        order.setCart(cart);
        order.setCustomer(customer);
        order.setId(orderDatabase.getLastID() + 1);
        order.setShipDate(new Date());
        order.setStatus(OrderStatus.pending);
        order.setShippingAddresses(shippingAddress);
        Random random = new Random();
        order.setShippingFees(random.nextInt(100));
        order.setTotalProdPrice(cart.getTotalPrice());
        orderDatabase.addOrder(order);
        cartDatabase.removeCart(customer);
        return "Order added successfully with id: " + order.getId();
    }
}
