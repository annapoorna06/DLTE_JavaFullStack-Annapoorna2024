package company.banking.mobile;

public class Account {
    private int accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account(int accountNumber, double accountbalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountbalance;
        this.accountHolder = accountHolder;
    }
    public double getAccountbalance(){
        return accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
