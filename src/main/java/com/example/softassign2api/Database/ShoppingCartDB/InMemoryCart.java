package com.example.softassign2api.Database.ShoppingCartDB;

import com.example.softassign2api.Database.CategoryDB.InMemoryCategory;
import com.example.softassign2api.Models.Order.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InMemoryCart implements ICartDatabase {
    private static final ArrayList<ShoppingCart> carts = new ArrayList<>();

    @Override
    public String addToCart(String id, String name, String vendor, int amount) {
        ShoppingCart cart = getCart(id);
        if (cart != null) {
            int index = carts.indexOf(cart);
            return carts.get(index).addProduct(name, vendor, amount);
        } else {
            cart = new ShoppingCart(new InMemoryCategory(), id);
            String out = cart.addProduct(name, vendor, amount);
            carts.add(cart);
            return out;
        }
    }

    @Override
    public String delFromCart(String id, String name, String vendor, int amount) {
        ShoppingCart cart = getCart(id);
        if (cart != null) {
            int index = carts.indexOf(cart);
            return carts.get(index).removeProduct(name, vendor, amount);
        }
        return "Error: Cart is empty!";
    }

    @Override
    public boolean removeCart(String id) {
        ShoppingCart temp = getCart(id);
        if (temp != null) {
            carts.remove(temp);
            return true;
        }
        return false;
    }

    @Override
    public ShoppingCart getCart(String id) {
        for (ShoppingCart cart : carts) {
            if (cart.getId().equals(id)) {
                return cart;
            }
        }
        return null;
    }
}
