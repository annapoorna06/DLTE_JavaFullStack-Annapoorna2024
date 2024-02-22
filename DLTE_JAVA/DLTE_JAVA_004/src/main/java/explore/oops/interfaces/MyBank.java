package explore.oops.interfaces;

public interface MyBank {
    void addNewLoan(Loan loan);
    Loan[] checkAvailableLoans();
    Loan[] checkClosedLoans();
}
