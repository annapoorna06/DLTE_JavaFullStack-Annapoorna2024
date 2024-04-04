package mybank.dao.controller;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanServices loanServices;

    @GetMapping("/all")
    public List<LoansAvailable> findBySender() {
        List<LoansAvailable> loans=loanServices.allAvailableLoans();
        System.out.println(loans.toString());
        return loans;

    }
}
