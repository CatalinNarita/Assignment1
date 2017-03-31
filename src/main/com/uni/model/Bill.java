package com.uni.model;

/**
 * Created by catal on 3/31/2017.
 */
public class Bill {

    private int billId;
    private String company;
    private double amount;
    private int clientId;

    public Bill(){}

    public Bill(int billId, String company, double amount, int clientId) {
        this.billId = billId;
        this.company = company;
        this.amount = amount;
        this.clientId = clientId;
    }

    public Bill(String company, double amount){
        this.company = company;
        this.amount = amount;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
