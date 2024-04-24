package loans;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
@RequestScoped
public class Loans {
    private Long loanNumber;
    private Double loanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;
    private List<Loans> loanList;

    @PostConstruct
    public void initLoans() {
        loanList = new ArrayList<>();
        loanList.add(new Loans(100L, 10000.0, "03/03/2024", "open", "Annapoorna", 6363276256L));
        loanList.add(new Loans(101L, 200000.0, "05/03/2024", "open", "Akshira", 9487362738L));
        loanList.add(new Loans(102L, 126667.0, "06/03/2024", "open", "Akshatha", 9353523995L));
        loanList.add(new Loans(103L, 25000.0, "07/03/2024", "closed", "Arundhathi", 8104726372L));
    }

    public Loans(Long loanNumber, Double loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }
    public Loans() {

    }
//methods
    public void addLoan(Loans loan) {
        loanList.add(loan);
    }

    public List<Loans> getClosedLoans() {
        List<Loans> closedLoans = new ArrayList<>();
        for (Loans loan : loanList) {
            if (loan.getLoanStatus().equalsIgnoreCase("closed")) {
                closedLoans.add(loan);
            }
        }
        return closedLoans;
    }

    public void deleteLoan(Long loanNumber) {
        loanList.removeIf(loan -> loan.getLoanNumber().equals(loanNumber));
    }

    public String allLoans(){
        return loanList.toString();
    }


    @Override
    public String toString() {
        return "Loans{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                '}';
    }


    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
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

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }



}