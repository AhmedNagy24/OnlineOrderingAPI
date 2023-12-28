package com.example.softassign2api.Models;

import java.util.Date;

abstract class Order {
    protected int id;
    protected Date orderDate;
    protected OrderStatus status;
    protected String shippingAddresses;
    protected String customer;
    protected double shippingFees;
}
