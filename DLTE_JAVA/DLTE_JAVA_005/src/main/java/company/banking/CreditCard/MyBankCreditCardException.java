package company.banking.CreditCard;

import java.util.ResourceBundle;

public class MyBankCreditCardException extends Throwable {
    public MyBankCreditCardException(){

        super(ResourceBundle.getBundle("account").getString("filter.exceed"));
    }
}
