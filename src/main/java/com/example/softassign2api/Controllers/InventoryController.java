package com.example.softassign2api.Controllers;

import com.example.softassign2api.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/get-inventory")
    public Object getInventory(){
        return inventoryService.getInventory();
    }
}
