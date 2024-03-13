package org.example.Exceptions;

import java.util.ResourceBundle;
// exception class to handle all the user defined exceptions
public class UserDetailsException extends RuntimeException {
    public UserDetailsException(){
        super(ResourceBundle.getBundle("userdetails").getString("user.exception"));
    }
    public UserDetailsException(String additionalInfo){
        super(ResourceBundle.getBundle("userdetails").getString("user.exception")+" "+additionalInfo);
    }
}
