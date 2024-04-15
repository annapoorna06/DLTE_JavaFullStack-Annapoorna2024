package mybank.dao.services;

import mybank.dao.entity.MyBankCustomers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;
@Service
public class MyBankCustomersService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);

    public MyBankCustomers signingUp(MyBankCustomers myBankCustomers){
        int ack = jdbcTemplate.update(
                "INSERT INTO MYBANK_APP_CUSTOMER VALUES (CUSTOMERID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)",
                new Object[]{
                        myBankCustomers.getCustomerName(),
                        myBankCustomers.getCustomerAddress(),
                        myBankCustomers.getCustomerStatus(),
                        myBankCustomers.getCustomerContact(),
                        myBankCustomers.getUsername(),
                        myBankCustomers.getPassword(),
                        myBankCustomers.getAttempts()
                });
        return myBankCustomers;
    }

    public MyBankCustomers findByUsername(String username){
        MyBankCustomers myBankCustomers = jdbcTemplate.queryForObject(
                "SELECT * FROM MYBANK_APP_CUSTOMER WHERE USERNAME=?",
                new Object[]{username},
                new BeanPropertyRowMapper<>(MyBankCustomers.class)
        );
        return myBankCustomers;
    }

    public void updateAttempts(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankCustomers.getAttempts(),myBankCustomers.getUsername()});
        logger.info(resourceBundle.getString("attempts.update"));
    }

    public void updateStatus(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='Inactive' where username=?",
                new Object[]{myBankCustomers.getUsername()});
        logger.info(resourceBundle.getString("status.changed"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers customers = findByUsername(username);
        if(customers==null)
            throw new UsernameNotFoundException(username);
        return customers;
    }

}
