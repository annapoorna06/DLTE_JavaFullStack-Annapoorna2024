package org.example;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private double amountInTransaction;
    private String transactionTo;
    private String remarks;

    //constructor
    public Transaction(Date dateOfTransaction, double amountInTransaction, String transactionTo, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.transactionTo = transactionTo;
        this.remarks = remarks;
    }
    //getters and setters

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
