package com.uni.model;

import java.util.Date;

/**
 * Created by catal on 3/26/2017.
 */
public class Account {

    private int accountId;
    private String accNumber;
    private String type;
    private double amount;
    private Date creationDate;
    private int clientId;

    public Account(){}

    public Account(int accountId, String accNumber, String type, double amount, Date creationDate, int clientId) {
        this.accountId = accountId;
        this.accNumber = accNumber;
        this.type = type;
        this.amount = amount;
        this.creationDate = creationDate;
        this.clientId = clientId;
    }

    public Account(String accNumber,String type, double amount, Date creationDate, int clientId) {
        this.accNumber = accNumber;
        this.type = type;
        this.amount = amount;
        this.creationDate = creationDate;
        this.clientId = clientId;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accNumber='" + accNumber + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                ", clientId=" + clientId +
                '}';
    }
}
