package mybank.dao.controller;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.services.LoanServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        LoanServices loanServices = new LoanServices();

        // Test allAvailableLoans() method
        System.out.println("All Available Loans:");
        List<LoansAvailable> allLoans = loanServices.allAvailableLoans();
        System.out.println(allLoans.size());
        if (allLoans != null) {
            for (LoansAvailable loan : allLoans) {
                System.out.println(loan);
            }
        }

//        // Test findByLoanType() method
//        System.out.println("\nLoans by Loan Type:");
//        String loanType = "Home Loan"; // Change loan type as needed
//        List<LoansAvailable> loansByType = loanServices.findByLoanType(loanType);
//        if (loansByType != null) {
//            for (LoansAvailable loan : loansByType) {
//                System.out.println(loan);
//            }
//        }
    }
}
