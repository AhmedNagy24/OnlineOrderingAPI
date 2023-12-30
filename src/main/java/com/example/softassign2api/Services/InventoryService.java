package com.example.softassign2api.Services;

import com.example.softassign2api.Database.CategoryDB.ICategoryDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    ICategoryDatabase categoryDatabase;
    public InventoryService(@Qualifier("inMemoryCategory") ICategoryDatabase db){
        categoryDatabase = db;
    }
    public Object getInventory(){
        return categoryDatabase.serializeCategories();
    }
}
