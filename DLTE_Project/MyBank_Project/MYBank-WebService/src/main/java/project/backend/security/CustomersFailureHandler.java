package project.backend.security;

import mybank.dao.entity.MyBankCustomers;
import mybank.dao.services.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomersFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankCustomersService myBankCustomersService;
    Logger logger= LoggerFactory.getLogger(CustomersFailureHandler.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankCustomers myBankCustomers = myBankCustomersService.findByUsername(username);
        if(myBankCustomers!=null){
            if(!myBankCustomers.getCustomerStatus().equals("Inactive")){
                if(myBankCustomers.getAttempts()< myBankCustomers.getMaxAttempt()){
                    myBankCustomers.setAttempts(myBankCustomers.getAttempts()+1);
                    myBankCustomersService.updateAttempts(myBankCustomers);
                    logger.warn(resourceBundle.getString("credentials.invalid"));
                    exception=new LockedException(resourceBundle.getString("attempts.taken"));
                }
                else{
                    myBankCustomersService.updateStatus(myBankCustomers);
                    logger.warn(resourceBundle.getString("account.suspend"));
                    exception=new LockedException(resourceBundle.getString("account.suspend"));
                }
            }
            else{
                logger.warn(resourceBundle.getString("account.redeem"));
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
