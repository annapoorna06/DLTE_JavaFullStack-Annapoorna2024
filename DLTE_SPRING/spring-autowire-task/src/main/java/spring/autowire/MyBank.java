package spring.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyBank {
    // Autowiring HomeLoanImplementation using field injection
    @Autowired
    private PersonalLoanImplementation personalLoanImplementation;
    //private HomeLoanImplementation homeLoanImplementation;
    //@Qualifier("HomeLoanImplementation")
    @Qualifier("PersonalLoanImplementation")
    // Method to call the find method of HomeLoanImplementation
    public void execute() {
//        List<Loan> homeLoans = homeLoanImplementation.find();
//        System.out.println("Home Loans:");
//        for (Loan loan : homeLoans) {
//            System.out.println(loan);
//        }
        List<Loan> personalLoans = personalLoanImplementation.find();
        System.out.println("Personal Loans:");
        for (Loan loan : personalLoans) {
            System.out.println(loan);
        }
    }
}