package com.example.softassign2api.Services;


import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {
    @Autowired
    private CustomerDatabase customerDatabase;


    public String registerUser(Customer user) {
        if (customerDatabase.IfUserNameExists(user.getUserName())){
            return "Register failed...username already exists";
        }else {
            customerDatabase.add(user);
            return "Registered Successfully";
        }
    }

    public Customer SignInUser(String username,String password){

        return  customerDatabase.CheckCredentials(username,password);
    }


}
