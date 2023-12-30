package com.example.softassign2api.Models.Inventory;

import java.util.Map;
import java.util.Set;

public class Category {
    private String name;
    private final Map<Product, Integer> products;

    public Category(String name, Map<Product, Integer> products) {
        this.products = products;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean containsProduct(Product product) {
        return products.containsKey(product);
    }

    public int getPartsNum(Product product) {
        return products.get(product);
    }

    public void updatePartsNum(Product product, int amount) {
        products.put(product, amount);
    }

    public Set<Product> getProductsSet() {
        return products.keySet();
    }

    public int calcTotalParts() {
        int total = 0;
        for (int i : products.values()) {
            total += i;
        }
        return total;
    }
}
