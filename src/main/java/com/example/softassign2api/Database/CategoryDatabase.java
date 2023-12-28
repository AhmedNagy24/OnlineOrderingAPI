package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Category;

public interface CategoryDatabase {
    String decPartsNum(String id, int decrement);
    String incPartsNum(String id, int increment);
    Category getCategories();
}
