package com.example.softassign2api.Models;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private String name;
    private Map<Product, Integer> products;
    Category(String name){
        products = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
    public int calcTotalParts(){
        int total = 0;
        for (int i : products.values()){
            total+=i;
        }
        return total;
    }
}
