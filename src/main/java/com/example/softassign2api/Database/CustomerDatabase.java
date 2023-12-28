package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Customer;

public interface CustomerDatabase {
    void add(Customer customer);
    Customer getCustomer(String userName);

    boolean IfUserNameExists(String username);

    Customer CheckCredentials(String username, String password);
}
