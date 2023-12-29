package com.example.softassign2api.Database;
import com.example.softassign2api.Models.Customer;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Objects;

@Component
public class InMemoryCustomer implements CustomerDatabase{
    private static ArrayList<Customer> customers = new ArrayList<>();
    public InMemoryCustomer(){
        customers.add(new Customer("user1","user123456",1000,false));
        customers.add(new Customer("user2","user123456",1000,false));
        customers.add(new Customer("user3","user123456",1000,false));
        customers.add(new Customer("user4","user123456",1000,false));
        customers.add(new Customer("user5","user123456",1000,false));
    }


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

    @Override
    public boolean IfUserNameExists(String username) {
        for (Customer temp : customers) {
            if (Objects.equals(temp.getUserName(), username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer CheckCredentials(String username,String password) {
        for (Customer temp : customers) {
            if (temp.getUserName().equals(username) && temp.getPassword().equals(password)){
                return temp;
            }
        }
        return null;
    }
}
