package mybank.dao.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class LoansAvailed {
    //entities form MYBANK_APP_LOANAVAILED table
    @NotNull(message="{avail.num.null}")
    @Digits(integer=3,fraction=0,message="{avail.num.invalid}")
    private int loanAvailNumber;
    @NotNull(message="{customer.num.null}")
    @Digits(integer=3,fraction=0,message="{customer.num.invalid}")
    private int customerNumber;
    private int loanNumber;
    private double loanAmount;
    private double loanEmi;
    private int loanTenure;

    //constructor
    public LoansAvailed(int loanAvailNumber, int customerNumber, int loanNumber, double loanAmount, double loanEmi, int loanTenure) {

        this.loanAvailNumber = loanAvailNumber;
        this.customerNumber = customerNumber;
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanEmi = loanEmi;
        this.loanTenure = loanTenure;
    }

    //empty constructor
    public LoansAvailed() {
    }

    // to string
    @Override
    public String toString() {
        return "LoansAvailed{" +
                "loanAvailNumber=" + loanAvailNumber +
                ", customerNumber=" + customerNumber +
                ", loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanEmi=" + loanEmi +
                ", loanTenure=" + loanTenure +
                '}';
    }

    //getters and setters
    public int getLoanAvailNumber() {
        return loanAvailNumber;
    }

    public void setLoanAvailNumber(int loanAvailNumber) {
        this.loanAvailNumber = loanAvailNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanEmi() {
        return loanEmi;
    }

    public void setLoanEmi(double loanEmi) {
        this.loanEmi = loanEmi;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }
}
