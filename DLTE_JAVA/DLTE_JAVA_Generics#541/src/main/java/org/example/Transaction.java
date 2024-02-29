package org.example;

public class Transaction {
    private int id;
    private double amount;
    private String date;

    public Transaction(int id, double amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
