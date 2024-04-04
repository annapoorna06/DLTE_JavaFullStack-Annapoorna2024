package mybank.dao.exceptions;

public class NoLoanDataException extends RuntimeException {
    public NoLoanDataException(String message){
        super(message);
    }
}
