package explore.oops.interfaces;

public class Loan {
    private int loanNumber;
    private double leanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private String borrowerContact;

    public Loan(int loanNumber, double leanAmount, String loanDate, String loanStatus, String borrowerName, String borrowerContact) {
        this.loanNumber = loanNumber;
        this.leanAmount = leanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }
    public int getLoanNumber() {
        return loanNumber;
    }
    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }
    public double getLeanAmount() {
        return leanAmount;
    }
    public void setLeanAmount(double leanAmount) {
        this.leanAmount = leanAmount;
    }
    public String getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
    public String getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
    public String getBorrowerName() {
        return borrowerName;
    }
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    public String getBorrowerContact() {
        return borrowerContact;
    }
    public void setBorrowerContact(String borrowerContact) {
        this.borrowerContact = borrowerContact;
    }
    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", leanAmount=" + leanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact='" + borrowerContact + '\'' +
                '}';
    }
     class MyAccount{
        public String Name;

    }
}
