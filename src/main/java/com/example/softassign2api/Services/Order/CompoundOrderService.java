package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import com.example.softassign2api.Database.CategoryDB.InMemoryCategory;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Database.CustomerDB.InMemoryCustomer;
import com.example.softassign2api.Database.NotificationDB.INotificationDatabase;
import com.example.softassign2api.Database.OrderDB.IOrderDatabase;
import com.example.softassign2api.Database.ShoppingCartDB.ICartDatabase;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Notification.NotificationTemplate;
import com.example.softassign2api.Models.Notification.PlacedNotification;
import com.example.softassign2api.Models.Notification.ShippedNotification;
import com.example.softassign2api.Models.Order.*;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CompoundOrderService extends OrderService {
    public CompoundOrderService(IOrderDatabase orderDb, ICartDatabase cartDb, ICustomerDatabase customerDb, ICategoryDatabase categoryDb, INotificationDatabase notificationDb) {
        super(orderDb, cartDb, customerDb, categoryDb, notificationDb);
    }
    @Override
    public String placeOrder(int id) {
        if (orderDatabase.getOrder(id) == null){
            return "Error: Order ID: "+id+" does not exist";
        }
        if (orderDatabase.getOrder(id).getStatus() != OrderStatus.pending){
            return "Error: Order ID: "+id+" cannot be placed";
        }
        ArrayList<Order> orders = ((CompoundOrder)orderDatabase.getOrder(id)).getOrders();
        for (Order order : orders) {
            String customer = order.getCustomer();
            ShoppingCart cart = ((SimpleOrder)order).getCart();
            double totalPrice = cart.getTotalPrice();
            if (!customerDatabase.canDecreaseBalance(customer, totalPrice)){
                return "Error: Not enough balance to place order\n Total price: "+totalPrice;
            }
            for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
                if(!categoryDatabase.canRemove(entry.getKey(), entry.getValue())){
                    return "Error: Not enough parts in stock for "+entry.getKey()+" from "+entry.getKey();
                }
            }
        }
        for (Order order : orders) {
            String customer = order.getCustomer();
            ShoppingCart cart = ((SimpleOrder)order).getCart();
            double totalPrice = cart.getTotalPrice();
            for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
                categoryDatabase.decPartsNum(entry.getKey(), entry.getValue());
            }
            customerDatabase.decreaseBalance(customer, totalPrice);
            order.setStatus(OrderStatus.placed);
            NotificationTemplate template = new PlacedNotification(customer);
            notificationDatabase.saveNotification(customerDatabase.getCustomer(customer), template);
        }
        orderDatabase.getOrder(id).setStatus(OrderStatus.placed);
        return "Order ID: "+id+" placed successfully";
    }
    @Override
    public String shipOrder(int id) {
        if (orderDatabase.getOrder(id) == null){
            return "Error: Order ID: "+id+" does not exist";
        }
        if (orderDatabase.getOrder(id).getStatus() != OrderStatus.placed){
            return "Error: Order ID: "+id+" cannot be shipped";
        }
        ArrayList<Order> orders = ((CompoundOrder)orderDatabase.getOrder(id)).getOrders();
        for (Order order : orders) {
            String customer = order.getCustomer();
            double shippingFees = order.getShippingFees();
            if (!customerDatabase.canDecreaseBalance(customer, shippingFees)){
                return "Error: Not enough balance to ship order\n Shipping fees: "+shippingFees;
            }
        }
        for (Order order : orders) {
            String customer = order.getCustomer();
            double shippingFees = order.getShippingFees();
            customerDatabase.decreaseBalance(customer, shippingFees);
            order.setStatus(OrderStatus.shipped);
            order.setShipDate(new Date());
            NotificationTemplate template = new ShippedNotification(customer);
            notificationDatabase.saveNotification(customerDatabase.getCustomer(customer), template);
        }
        orderDatabase.getOrder(id).setStatus(OrderStatus.shipped);
        orderDatabase.getOrder(id).setShipDate(new Date());
        return "Order ID: "+id+" shipped successfully";
    }
    @Override
    public String cancelOrder(int id) {
        Order order = orderDatabase.getOrder(id);
        if(order == null){
            return "Error: Order ID: "+id+" does not exist";
        }
        if (order.getStatus() == OrderStatus.placed){
            OrderActionContext context = new OrderActionContext(new CancelCompoundPlaced(new InMemoryCustomer(), new InMemoryCategory()));
            return context.executeAction(orderDatabase.getOrder(id));
        } else if (order.getStatus() == OrderStatus.shipped){
            OrderActionContext context = new OrderActionContext(new CancelCompoundShipped(new InMemoryCustomer()));
            return context.executeAction(orderDatabase.getOrder(id));
        } else {
            return "Error: Order ID: "+id+" cannot be cancelled";
        }
    }
    @Override
    public Object getOrder(int id) {
        if (orderDatabase.getOrder(id) == null){
            return "Error: Order ID: "+id+" does not exist";
        }
        ArrayList<Order> orders = ((CompoundOrder)orderDatabase.getOrder(id)).getOrders();
        ArrayList<Object> ordersInfo = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> tempInfo = new HashMap<>();
            tempInfo.put("id", order.getId());
            tempInfo.put("shipDate", order.getShipDate());
            tempInfo.put("status", order.getStatus());
            tempInfo.put("shippingAddresses", order.getShippingAddresses());
            tempInfo.put("customer", order.getCustomer());
            tempInfo.put("shippingFees", order.getShippingFees());
            tempInfo.put("totalProdPrice", order.getTotalProdPrice());
            tempInfo.put("cart", getCartInfo(order));
            ordersInfo.add(tempInfo);
        }
        return ordersInfo;
    }
    public String addOrder(String userName, String userAddress, Map<String, String> customers){
        if (customers.isEmpty()){
            return "Error: No customers in order";
        }
        if (customerDatabase.getCustomer(userName) == null){
            return "Error: Customer "+userName+" does not exist";
        }else if (!customerDatabase.getCustomer(userName).getLogin()){
            return "Error: Customer "+userName+" is not logged in";
        }
        for (Map.Entry<String, String> entry : customers.entrySet()) {
            if (customerDatabase.getCustomer(entry.getKey()) == null){
                return "Error: Customer "+entry.getKey()+" does not exist";
            }
        }
        CompoundOrder compOrder = new CompoundOrder();
        Random random = new Random();
        int shippingFees = random.nextInt(100);
        int id = orderDatabase.getLastID()+1;
        ArrayList<String> cartsToRemove = new ArrayList<>();
        customers.put(userName, userAddress);
        shippingFees /= customers.size();
        for (Map.Entry<String, String> entry : customers.entrySet()) {
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
        compOrder.setCustomer(userName);
        compOrder.setShippingAddresses(userAddress);
        compOrder.setTotalProdPrice(compOrder.getTotalProdPrice());
        orderDatabase.addOrder(compOrder);
        return "Order added successfully with id: "+compOrder.getId();
    }
}
