package spring.autowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("HomeLoanImplementation")
public class HomeLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> find() {
        // Simulate finding home loans
        List<Loan> homeLoans = new ArrayList<>();
        homeLoans.add(new Loan(1L,500000L,"open","home","Raksha",8217696845L));
        homeLoans.add(new Loan(2L, 200000L,"open","home","arundhathi",9081237645L));
        return homeLoans;
    }
}
