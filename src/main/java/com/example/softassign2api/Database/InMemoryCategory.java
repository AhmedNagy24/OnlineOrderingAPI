package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Category;
import com.example.softassign2api.Models.Product;

import java.util.ArrayList;

public class InMemoryCategory implements CategoryDatabase{
    private static final ArrayList<Category> categories = new ArrayList<>();

    @Override
    public boolean decPartsNum(Product product, int decrement) {
        for (Category cat:categories) {
            if (cat.getProducts().containsKey(product)){
                int oldValue = cat.getProducts().get(product);
                int indexCat = categories.indexOf(cat);
                if (oldValue > 0 && oldValue >= decrement){
                    categories.get(indexCat).getProducts().put(product, oldValue-decrement);
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
            if (cat.getProducts().containsKey(product)){
                int indexCat = categories.indexOf(cat);
                int oldValue = cat.getProducts().get(product);
                categories.get(indexCat).getProducts().put(product, oldValue+increment);
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
            for (Product product : cat.getProducts().keySet()) {
                if (product.getName().equals(name) && product.getVendor().equals(vendor)) {
                    return product;
                }
            }
        }
        return null;
    }
}
