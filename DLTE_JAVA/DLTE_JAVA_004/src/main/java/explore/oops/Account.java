package explore.oops;

public class Account {
    private int accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account(int accountNumber, double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
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
    //checks balance to approve withdraw
    public boolean approveWithdraw(double amount){
        System.out.println("your current balance is "+accountBalance);
        return amount<=accountBalance;
    }
}
