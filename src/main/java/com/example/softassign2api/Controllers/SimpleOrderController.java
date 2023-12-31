package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.Order.OrderService;
import com.example.softassign2api.Services.Order.SimpleOrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simple-order")
public class SimpleOrderController {
    private final OrderService orderService;

    public SimpleOrderController(@Qualifier("simpleOrderService") OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/place/{id}")
    public String placeOrder(@PathVariable("id") int id) {
        return orderService.placeOrder(id);
    }

    @PutMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id) {
        return orderService.cancelOrder(id);
    }

    @PutMapping("/ship/{id}")
    public String shipOrder(@PathVariable("id") int id) {
        return orderService.shipOrder(id);
    }

    @PostMapping("/add/{userName}/{address}")
    public String addOrder(@PathVariable("userName") String userName, @PathVariable("address") String address) {
        return ((SimpleOrderService) orderService).addOrder(userName, address);
    }

    @GetMapping("/simple-order/get/{id}")
    public Object getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }
}
