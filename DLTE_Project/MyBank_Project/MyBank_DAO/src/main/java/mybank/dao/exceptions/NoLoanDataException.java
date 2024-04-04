package mybank.dao.exceptions;

public class NoLoanDataException extends RuntimeException {
    public NoLoanDataException(){
        super("No Loans found!");
    }
}
