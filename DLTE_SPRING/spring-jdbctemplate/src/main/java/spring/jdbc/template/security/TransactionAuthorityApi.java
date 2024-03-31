package spring.jdbc.template.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class TransactionAuthorityApi {
    @Autowired
    TransactionAuthorityService transactionAuthorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public TransactionAuthority save(@RequestBody TransactionAuthority transactionAuthority){
        transactionAuthority.setPassword(passwordEncoder.encode(transactionAuthority.getPassword()));
        return transactionAuthorityService.signingUp(transactionAuthority);
    }

}
