package mybank.dao.exceptions;


import java.util.ResourceBundle;

public class LoanServiceException extends RuntimeException {

    public LoanServiceException(String message){
        super(message);
    }

}
