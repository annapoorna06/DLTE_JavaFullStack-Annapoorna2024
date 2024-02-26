package company.banking.mobile;

import sun.util.logging.PlatformLogger;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GPay extends Account{
    private String upiPin;
    private String username;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("account");
    Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public GPay(int accountNumber, double accountbalance, String accountHolder, String upiPin) {
        super(accountNumber, accountbalance, accountHolder);
        this.upiPin = upiPin;
        this.username = accountHolder;
    }
    public boolean validateUpiPin(String enteredUpiPin) throws MyBankException{
        if (!upiPin.equals(enteredUpiPin)){
            logger.log(Level.WARNING,"Invalid UPI pin entered for account number"+getAccountNumber());
            throw new MyBankException("upipin.invalid");
        }
        return true;
    }
    public void payBill(String billName, double billAmount, String billType, String upiPin) throws MyBankException {
        try{
            validateUpiPin(upiPin);
            if (getAccountBalance()>=billAmount){
                logger.log(Level.INFO, "Bill Payment approved for "+billType+" biller "+billName+" amount "+billAmount);
            }
            else{
                logger.log(Level.WARNING,"fund insufficient for bill payment "+getAccountBalance());
                throw new MyBankException("upipin.invalid");
            }
        } catch (MyBankException exception) {
            logger.log(Level.WARNING, exception.toString());
            throw exception;
        }
    }
    public String getUsername(){
        return username;
    }
}
