package company.banking.mobile;

import java.util.ResourceBundle;
    public class MyBankException extends Exception{
        //    private static String message;

        public MyBankException(String message){
            super(ResourceBundle.getBundle("application").getString("exception.account"));
        }
    }

