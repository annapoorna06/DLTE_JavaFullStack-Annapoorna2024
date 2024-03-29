package jdbc.soapweb.dao;

import java.util.Date;

public class Transactions {
    private int transactionId;
    private Date transactionDate;
    private String transactionTo;
    private double transactionAmount;
    private String transactionRemarks;
    private String transactionBy;

    public Transactions(int transactionId, Date transactionDate, String transactionTo, double transactionAmount, String transactionRemarks, String transactionBy) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
        this.transactionBy = transactionBy;
    }

    public Transactions() {
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                '}';
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }
}
