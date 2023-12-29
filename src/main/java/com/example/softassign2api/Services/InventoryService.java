package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CategoryDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    CategoryDatabase categoryDatabase;
    public InventoryService(@Qualifier("inMemoryCategory") CategoryDatabase db){
        categoryDatabase = db;
    }
    public Object getInventory(){
        return categoryDatabase.serializeCategories();
    }
}
