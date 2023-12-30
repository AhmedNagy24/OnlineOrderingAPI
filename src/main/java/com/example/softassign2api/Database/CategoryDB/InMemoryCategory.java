package com.example.softassign2api.Database.CategoryDB;

import com.example.softassign2api.Models.Inventory.Category;
import com.example.softassign2api.Models.Inventory.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCategory implements ICategoryDatabase {
    private static final ArrayList<Category> categories = new ArrayList<>();

    static {
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
    public boolean canRemove(Product product, int decrement) {
        for (Category cat : categories) {
            if (cat.containsProduct(product)) {
                int oldValue = cat.getPartsNum(product);
                return oldValue > 0 && oldValue >= decrement;
            }
        }
        return false;
    }

    @Override
    public boolean decPartsNum(Product product, int decrement) {
        for (Category cat : categories) {
            if (cat.containsProduct(product)) {
                int indexCat = categories.indexOf(cat);
                int oldValue = cat.getPartsNum(product);
                if (oldValue >= decrement) {
                    categories.get(indexCat).updatePartsNum(product, oldValue - decrement);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean incPartsNum(Product product, int increment) {
        for (Category cat : categories) {
            if (cat.containsProduct(product)) {
                int indexCat = categories.indexOf(cat);
                int oldValue = cat.getPartsNum(product);
                categories.get(indexCat).updatePartsNum(product, oldValue + increment);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Object> serializeCategories() {
        ArrayList<Object> serializedCats = new ArrayList<>();
        for (Category cat : categories) {
            Map<String, Object> tempCat = new HashMap<>();
            tempCat.put("name", cat.getName());
            tempCat.put("totalParts", cat.calcTotalParts());
            ArrayList<Object> serializedProds = new ArrayList<>();
            for (Product product : cat.getProductsSet()) {
                Map<String, Object> tempProd = new HashMap<>();
                tempProd.put("name", product.getName());
                tempProd.put("vendor", product.getVendor());
                tempProd.put("price", product.getPrice());
                tempProd.put("partsNum", cat.getPartsNum(product));
                serializedProds.add(tempProd);
            }
            tempCat.put("products", serializedProds);
            serializedCats.add(tempCat);
        }
        return serializedCats;
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
