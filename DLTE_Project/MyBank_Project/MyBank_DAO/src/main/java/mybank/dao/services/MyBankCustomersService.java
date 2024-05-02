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

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class MyBankCustomersService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers customers = findByUsername(username);
        if(customers==null)
            throw new UsernameNotFoundException(username);
        return customers;
    }

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
        List<MyBankCustomers> myBankCustomersList = jdbcTemplate.query(
                "SELECT * FROM MYBANK_APP_CUSTOMER", new BeanPropertyRowMapper<>(MyBankCustomers.class));
        MyBankCustomers customers=sortByUsername(myBankCustomersList,username);
        if(customers==null){
            throw new UsernameNotFoundException(username);
        }
        return customers;
    }

    public MyBankCustomers sortByUsername(List<MyBankCustomers> myBankCustomersList, String username){
        List<MyBankCustomers> myBankFilteredCustomers=myBankCustomersList.stream().filter(myBankCustomers -> myBankCustomers.getUsername()
                .equals(username)).collect(Collectors.toList());
        if (myBankFilteredCustomers.size()==0)
            return null;
        else{
            MyBankCustomers myBankCustomers=myBankFilteredCustomers.get(0);
            logger.info(resourceBundle.getString("no.customer"));
            return myBankCustomers;
        }
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
}
