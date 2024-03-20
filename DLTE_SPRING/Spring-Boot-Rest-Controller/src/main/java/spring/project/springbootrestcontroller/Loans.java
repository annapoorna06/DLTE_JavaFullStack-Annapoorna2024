package spring.project.springbootrestcontroller;

public class Loans {
    private String Name;
    private long loanNumber;
    private double loanAmount;

    public Loans(String name, long loanNumber, double loanAmount) {
        Name = name;
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "Name='" + Name + '\'' +
                ", loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}
