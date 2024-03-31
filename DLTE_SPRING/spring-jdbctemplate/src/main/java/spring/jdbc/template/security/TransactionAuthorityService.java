package spring.jdbc.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TransactionAuthorityService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TransactionAuthority signingUp(TransactionAuthority transactionAuthority){
        int ack=jdbcTemplate.update("insert into Transaction_user(name, username, password, email, contact, aadhaar,role) values(?,?,?,?,?,?,?)",new Object[]{
                transactionAuthority.getName(),transactionAuthority.getUsername(),transactionAuthority.getPassword(),
                transactionAuthority.getEmail(),transactionAuthority.getContact(),transactionAuthority.getRole()
        });
        return transactionAuthority;
    }
    public TransactionAuthority findByUsername(String username){
        TransactionAuthority transactionAuthority=jdbcTemplate.queryForObject("select * from Transaction_user where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(TransactionAuthority.class));
        return transactionAuthority;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TransactionAuthority transactionAuthority=findByUsername(username);
        if(transactionAuthority==null)
            throw new UsernameNotFoundException(username);
        return transactionAuthority;
    }
}
