package com.example.softassign2api.Models.Order;

import java.util.Date;

public abstract class Order {
    protected int id;
    protected Date shipDate = null;
    protected OrderStatus status;
    protected String shippingAddresses;
    protected String customer;
    protected double shippingFees;
    protected double totalProdPrice;
    abstract public double getTotalProdPrice();
    public void setTotalProdPrice(double totalProdPrice) {
        this.totalProdPrice = totalProdPrice;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
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
