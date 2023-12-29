package com.example.softassign2api.Controllers;

import com.example.softassign2api.Models.Customer;
import com.example.softassign2api.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public String registerUser(@RequestBody Customer user) {
        return customerService.registerUser(user);
    }
    @GetMapping("/login/{username}/{password}")
    public String LoginUser(@PathVariable("username") String username,@PathVariable("password")String password) {
        return customerService.SignInUser(username, password);
    }
}
