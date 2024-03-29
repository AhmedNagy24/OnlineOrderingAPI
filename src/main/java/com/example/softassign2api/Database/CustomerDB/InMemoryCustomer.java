package com.example.softassign2api.Database.CustomerDB;

import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Models.Notification.NotificationChannel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class InMemoryCustomer implements ICustomerDatabase {
    private static final ArrayList<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer("admin", "user123456", 1000.0,  NotificationChannel.EMAIL, "user1@gmail.com", "1234567890", "en"));
        customers.add(new Customer("user1", "user8910", 1000.0,  NotificationChannel.SMS, "user2@gmail.com", "1234567890", "en"));
        customers.add(new Customer("user2", "user8910", 1000.0,  NotificationChannel.SMS, "user3@gmail.com", "1234567890", "en"));
        customers.add(new Customer("user3", "user8910", 1000.0,  NotificationChannel.BOTH, "user4@gmail.com", "1234567890", "en"));
        customers.add(new Customer("user4", "user8910", 1000.0,  NotificationChannel.EMAIL, "user5@gmail.com", "1234567890", "en"));
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer getCustomer(String userName) {
        for (Customer temp : customers) {
            if (temp.getUserName().equals(userName)) {
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
    public Customer CheckCredentials(String username, String password) {
        for (Customer temp : customers) {
            if (temp.getUserName().equals(username) && temp.getPassword().equals(password)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean decreaseBalance(String username, double amount) {
        for (Customer temp : customers) {
            if (temp.getUserName().equals(username)) {
                if (temp.getBalance() >= amount) {
                    temp.setBalance(temp.getBalance() - amount);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean increaseBalance(String username, double amount) {
        for (Customer temp : customers) {
            if (temp.getUserName().equals(username)) {
                temp.setBalance(temp.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canDecreaseBalance(String username, double amount) {
        Customer temp = getCustomer(username);
        if (temp == null) {
            return false;
        }
        return temp.getBalance() >= amount;
    }

}
