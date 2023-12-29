package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Product;

import java.util.ArrayList;

public interface CategoryDatabase {

    boolean canRemove(Product product, int decrement);
    boolean decPartsNum(Product product, int decrement);
    boolean incPartsNum(Product product, int increment);
    ArrayList<Object> serializeCategories();
    Product searchProd(String name, String vendor);

}
