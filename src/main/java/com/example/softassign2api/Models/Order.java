package com.example.softassign2api.Models;

import java.util.Date;

abstract class Order {
    protected int id;
    protected Date orderDate;
    protected OrderStatus status;
    protected String shippingAddresses;
    protected String customer;
    protected double shippingFees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(String shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }
}
