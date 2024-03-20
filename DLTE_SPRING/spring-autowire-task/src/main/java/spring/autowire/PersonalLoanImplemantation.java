package spring.autowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("PersonalLoanImplementation")
class PersonalLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> find() {
        // Simulate finding personal loans
        List<Loan> personalLoans = new ArrayList<>();
        personalLoans.add(new Loan(1L,10000L, "open","Gold","Annapoorna",6363276256L));
        personalLoans.add(new Loan(2L,30000L,"open","education","Akshatha",9353523995L));
        return personalLoans;
    }
}
