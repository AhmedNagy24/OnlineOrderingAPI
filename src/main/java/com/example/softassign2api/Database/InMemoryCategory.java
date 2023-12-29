package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Category;
import com.example.softassign2api.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCategory implements CategoryDatabase{
    private static final ArrayList<Category> categories = new ArrayList<>();

    public InMemoryCategory(){
        Map<Product, Integer> temp = new HashMap<>();
        temp.put(new Product("Shirt", "Nike", 100), 60);
        temp.put(new Product("Shirt", "Adidas", 100), 50);
        temp.put(new Product("Shirt", "Puma", 100), 40);
        temp.put(new Product("Shirt", "Reebok", 100), 30);
        categories.add(new Category("Clothes", temp));
        temp = new HashMap<>();
        temp.put(new Product("Laptop", "Dell", 1000), 60);
        temp.put(new Product("Laptop", "HP", 1000), 50);
        temp.put(new Product("Laptop", "Lenovo", 1000), 40);
        temp.put(new Product("Laptop", "Asus", 1000), 30);
        categories.add(new Category("Electronics", temp));
        temp = new HashMap<>();
        temp.put(new Product("Food", "Bread", 10), 60);
        temp.put(new Product("Food", "Milk", 10), 50);
        temp.put(new Product("Food", "Eggs", 10), 40);
        temp.put(new Product("Food", "Cheese", 10), 30);
        categories.add(new Category("Food", temp));
    }

    @Override
    public boolean decPartsNum(Product product, int decrement) {
        for (Category cat:categories) {
            if (cat.containsProduct(product)){
                int oldValue = cat.getPartsNum(product);
                int indexCat = categories.indexOf(cat);
                if (oldValue > 0 && oldValue >= decrement){
                    categories.get(indexCat).updatePartsNum(product, oldValue-decrement);
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean incPartsNum(Product product, int increment) {
        for (Category cat:categories) {
            if (cat.containsProduct(product)){
                int indexCat = categories.indexOf(cat);
                int oldValue = cat.getPartsNum(product);
                categories.get(indexCat).updatePartsNum(product, oldValue+increment);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public Product searchProd(String name, String vendor) {
        for (Category cat : categories) {
            for (Product product : cat.getProductsSet()) {
                if (product.getName().equals(name) && product.getVendor().equals(vendor)) {
                    return product;
                }
            }
        }
        return null;
    }
}
