package org.example;


import java.util.Date;

public class Transactions {
    private String user;
    private Date date;
    private double amount;

    public Transactions() {
    }

    public Transactions(String user, Date date, double amount) {
        this.user = user;
        this.date = date;
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
