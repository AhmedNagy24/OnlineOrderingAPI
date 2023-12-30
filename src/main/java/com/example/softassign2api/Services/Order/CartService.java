package com.example.softassign2api.Services.Order;

import com.example.softassign2api.Database.ShoppingCartDB.ICartDatabase;
import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Models.Inventory.Product;
import com.example.softassign2api.Models.Order.ShoppingCart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private ICartDatabase cartDatabase;
    private ICustomerDatabase customerDatabase;
    public CartService(@Qualifier("inMemoryCart") ICartDatabase cartDB, @Qualifier("inMemoryCustomer") ICustomerDatabase customerDB) {
        this.cartDatabase = cartDB;
        this.customerDatabase = customerDB;
    }

    public String addToCart(String id, String name, String vendor, int amount){
        Customer temp = customerDatabase.getCustomer(id);
        if (temp != null){
            if (temp.getLogin()){
                return cartDatabase.addToCart(id, name, vendor, amount);
            }else {
                return "Error Unauthorised action: "+id+" is not logged in";
            }
        }
        return "Error: "+id+" doesn't exist!";
    }
    public String delFromCart(String id, String name, String vendor, int amount){
        Customer temp = customerDatabase.getCustomer(id);
        if (temp != null){
            if (temp.getLogin()){
                return cartDatabase.delFromCart(id, name, vendor, amount);
            }else {
                return "Error Unauthorised action: "+id+" is not logged in";
            }
        }
        return "Error: "+id+" doesn't exist!";
    }
    public Object displayCart(String id){
        Customer temp = customerDatabase.getCustomer(id);
        if (temp != null){
            if (temp.getLogin()){
                ShoppingCart cart = cartDatabase.getCart(id);
                if (cart != null){
                    return serialize(cart);
                }
                return "Your cart is empty";
            }else {
                return "Error Unauthorised action: "+id+" is not logged in";
            }
        }
        return "Error: "+id+" doesn't exist!";
    }

    private static Map<String, Object> serialize(ShoppingCart cart) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", cart.getId());
        map.put("totalPrice", cart.getTotalPrice());
        ArrayList<Map<String, Object>> products = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry: cart.getCart().entrySet()){
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("name", entry.getKey().getName());
            tempMap.put("vendor", entry.getKey().getVendor());
            tempMap.put("amount", entry.getValue());
            products.add(tempMap);
        }
        map.put("products", products);
        return map;
    }
}
