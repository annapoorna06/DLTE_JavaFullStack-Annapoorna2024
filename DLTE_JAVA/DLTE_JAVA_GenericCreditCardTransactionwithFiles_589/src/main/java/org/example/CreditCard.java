package org.example;

import java.io.Serializable;

public class CreditCard implements Serializable {
    private String CardHolder;
    private long CardNumber;
    private int CardCvv;
    private double balance;

    //constructor
    public CreditCard(String cardHolder, long cardNumber, int cardCvv, double balance) {
        CardHolder = cardHolder;
        CardNumber = cardNumber;
        CardCvv = cardCvv;
        this.balance = balance;
    }
    //getters and setters

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long cardNumber) {
        CardNumber = cardNumber;
    }

    public int getCardCvv() {
        return CardCvv;
    }

    public void setCardCvv(int cardCvv) {
        CardCvv = cardCvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //toString() to display
    @Override
    public String toString() {
        return "CreditCard{" +
                "CardHolder='" + CardHolder + '\'' +
                ", CardNumber=" + CardNumber +
                ", CardCvv=" + CardCvv +
                ", balance=" + balance +
                '}';
    }
}
