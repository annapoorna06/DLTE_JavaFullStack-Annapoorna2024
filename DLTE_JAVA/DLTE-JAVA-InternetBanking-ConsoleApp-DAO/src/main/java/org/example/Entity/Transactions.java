package org.example.Entity;

import java.util.Date;

public class Transactions {
    private Date date;
    private long transactionID;
    private String username;
    private double amount;
    private double balance;

    public Transactions(Date date, long transactionID, String username, double amount, double balance) {
        this.date = date;
        this.transactionID = transactionID;
        this.username = username;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "date=" + date +
                ", transactionID=" + transactionID +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

