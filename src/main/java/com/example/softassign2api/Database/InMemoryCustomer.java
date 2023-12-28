package com.example.softassign2api.Database;

import com.example.softassign2api.Models.Customer;

import java.util.ArrayList;

public class InMemoryCustomer implements CustomerDatabase{
    private static final ArrayList<Customer> customers = new ArrayList<>();
    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer getCustomer(String userName) {
        for (Customer temp: customers) {
            if (temp.getUserName().equals(userName)){
                return temp;
            }
        }
        return null;
    }
}
