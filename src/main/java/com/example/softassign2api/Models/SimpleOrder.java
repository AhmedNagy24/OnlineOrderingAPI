package com.example.softassign2api.Models;

import java.util.ArrayList;

public class SimpleOrder extends Order {
    private ArrayList<Product> products;
    SimpleOrder(){
        products = new ArrayList<>();
    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
}
