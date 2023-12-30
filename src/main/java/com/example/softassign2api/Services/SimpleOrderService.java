package com.example.softassign2api.Services;

import com.example.softassign2api.Database.*;
import com.example.softassign2api.Models.OrderStatus;
import com.example.softassign2api.Models.ShoppingCart;
import com.example.softassign2api.Models.SimpleOrder;

import java.util.Date;
import java.util.Random;

public class SimpleOrderService extends OrderService {
    public SimpleOrderService(OrderDatabase orderDb, CartDatabase cartDb, CustomerDatabase customerDb) {
        super(orderDb, cartDb, customerDb);
    }

    @Override
    public String placeOrder(int id) {
        String customer = orderDatabase.getOrder(id).getCustomer();
        double totalPrice = cartDatabase.getCart(customer).getTotalPrice();
        if (customerDatabase.decreaseBalance(customer, totalPrice)){
            orderDatabase.getOrder(id).setStatus(OrderStatus.placed);
            return "Order ID: "+id+" placed successfully";
        }
        return "Error: Not enough balance to place order\n Total price: "+totalPrice;
    }

    @Override
    public String shipOrder(int id) {
        String customer = orderDatabase.getOrder(id).getCustomer();
        double shippingFees = orderDatabase.getOrder(id).getShippingFees();
        if (customerDatabase.decreaseBalance(customer, shippingFees)){
            orderDatabase.getOrder(id).setStatus(OrderStatus.shipped);
            orderDatabase.getOrder(id).setShipDate(new Date());
            return "Order ID: "+id+" shipped successfully";
        }
        return "Error: Not enough balance to ship order\n Shipping fees: "+shippingFees;
    }

    @Override
    public String cancelOrder(int id) {
        if (orderDatabase.getOrder(id).getStatus() == OrderStatus.placed){
            OrderActionContext context = new OrderActionContext(new CancelSimplePlaced(new InMemoryOrder(), new InMemoryCustomer()));
            return context.executeAction(id);
        } else if (orderDatabase.getOrder(id).getStatus() == OrderStatus.shipped){
            OrderActionContext context = new OrderActionContext(new CancelSimpleShipped(new InMemoryOrder(), new InMemoryCustomer()));
            return context.executeAction(id);
        } else {
            return "Error: Order ID: "+id+" cannot be cancelled";
        }
    }

    @Override
    public Object getOrder(int id) {
        return null;
    }

    public String addSimpleOrder(String customer, String shippingAddress){
        SimpleOrder order = new SimpleOrder();
        ShoppingCart cart = cartDatabase.getCart(customer);
        if (cart == null){
            return "Cart is empty";
        }
        order.setCart(cart);
        order.setCustomer(customer);
        order.setId(orderDatabase.getLastID()+1);
        order.setShipDate(new Date());
        order.setStatus(OrderStatus.pending);
        order.setShippingAddresses(shippingAddress);
        Random random = new Random();
        order.setShippingFees(random.nextInt(100));
        order.setTotalProdPrice(cart.getTotalPrice());
        orderDatabase.addOrder(order);
        cartDatabase.removeCart(customer);
        return "Order added successfully with id: "+order.getId();
    }
}
