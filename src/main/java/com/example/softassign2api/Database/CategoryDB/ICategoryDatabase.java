package com.example.softassign2api.Database.CategoryDB;

import com.example.softassign2api.Models.Inventory.Product;

import java.util.ArrayList;

public interface ICategoryDatabase {

    boolean canRemove(Product product, int decrement);

    boolean decPartsNum(Product product, int decrement);

    boolean incPartsNum(Product product, int increment);

    ArrayList<Object> serializeCategories();

    Product searchProd(String name, String vendor);

}
