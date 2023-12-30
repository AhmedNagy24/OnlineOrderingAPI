package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.CompoundOrderService;
import com.example.softassign2api.Services.OrderService;
import com.example.softassign2api.Services.SimpleOrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CompoundOrderController {
    private OrderService orderService;
    CompoundOrderController(@Qualifier("compoundOrderService") OrderService orderService){
        this.orderService = orderService;
    }
    @PutMapping("/compound-order/place/{id}")
    public String placeOrder(@PathVariable("id") int id) {
        return orderService.placeOrder(id);
    }
    @PutMapping("/compound-order/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id) {
        return orderService.cancelOrder(id);
    }
    @PutMapping("/compound-order/ship/{id}")
    public String shipOrder(@PathVariable("id") int id) {
        return orderService.shipOrder(id);
    }
    @PostMapping("/compound-order/add/{userName}/{address}")
    public String addOrder(@PathVariable("userName") String userName, @PathVariable("address") String address, @RequestBody Map<String, String> data) {
        return ((CompoundOrderService) orderService).addOrder(userName, address, data);
    }
    @GetMapping("/compound-order/get/{id}")
    public Object getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }
}
