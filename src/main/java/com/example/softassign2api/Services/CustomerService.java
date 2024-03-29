package com.example.softassign2api.Services;


import com.example.softassign2api.Database.CustomerDB.ICustomerDatabase;
import com.example.softassign2api.Models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    private final ICustomerDatabase customerDatabase;

    public CustomerService(@Qualifier("inMemoryCustomer") ICustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public String registerUser(Customer user) {
        if (customerDatabase.IfUserNameExists(user.getUserName())) {
            return "Register failed...username already exists";
        } else {
            customerDatabase.add(user);
            return "Registered Successfully";
        }
    }

    public String SignInUser(String username, String password) {

        if (customerDatabase.CheckCredentials(username, password) != null) {
            return "Login Successful";
        }
        return "Login Failed";
    }

}
