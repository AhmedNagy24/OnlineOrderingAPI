package com.example.softassign2api.Services;


import com.example.softassign2api.Database.CustomerDatabase;
import com.example.softassign2api.Database.InMemoryCustomer;
import com.example.softassign2api.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {
    @Autowired
    private InMemoryCustomer customerDatabase;


    public String registerUser(Customer user) {
        if (customerDatabase.IfUserNameExists(user.getUserName())){
            return "Register failed...username already exists";
        }else {
            customerDatabase.add(user);
            return "Registered Successfully";
        }
    }

    public String SignInUser(String username,String password){

          if(customerDatabase.CheckCredentials(username,password)!=null) {
              customerDatabase.CheckCredentials(username, password).setLogin(true);
              return "Login Successful";
          }
          return "Login Failed";
    }


}
