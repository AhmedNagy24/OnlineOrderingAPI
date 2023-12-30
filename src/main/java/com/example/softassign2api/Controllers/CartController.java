package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.Order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") String id, @RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        String vendor = (String) data.get("vendor");
        Integer amount = (Integer) data.get("amount");
        if (name == null || vendor == null || amount == null) {
            return "Error: Invalid request body";
        }
        return cartService.addToCart(id, name, vendor, amount);
    }

    @GetMapping("/get-cart/{id}")
    public Object getCart(@PathVariable("id") String id) {
        return cartService.displayCart(id);
    }

    @PutMapping("/remove-from-cart/{id}")
    public String removeFromCart(@PathVariable("id") String id, @RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        String vendor = (String) data.get("vendor");
        Integer amount = (Integer) data.get("amount");
        if (name == null || vendor == null || amount == null) {
            return "Error: Invalid request body";
        }
        return cartService.delFromCart(id, name, vendor, amount);
    }
}
