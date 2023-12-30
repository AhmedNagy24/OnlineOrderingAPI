package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.Order.OrderService;
import com.example.softassign2api.Services.Order.SimpleOrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleOrderController {
    private OrderService orderService;
    public SimpleOrderController(@Qualifier("simpleOrderService") OrderService orderService) {
        this.orderService = orderService;
    }
    @PutMapping("/simple-order/place/{id}")
    public String placeOrder(@PathVariable("id") int id) {
        return orderService.placeOrder(id);
    }
    @PutMapping("/simple-order/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id) {
        return orderService.cancelOrder(id);
    }
    @PutMapping("/simple-order/ship/{id}")
    public String shipOrder(@PathVariable("id") int id) {
        return orderService.shipOrder(id);
    }
    @PostMapping("/simple-order/add/{userName}/{address}")
    public String addOrder(@PathVariable("userName") String userName, @PathVariable("address") String address) {
        return ((SimpleOrderService) orderService).addOrder(userName, address);
    }
    @GetMapping("/simple-order/get/{id}")
    public Object getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }
}
