package mybank.dao.interfaces;

import mybank.dao.entity.LoansAvailable;

import java.util.List;

public interface LoansInterface {
    public List<LoansAvailable> allAvailableLoans();
    public List<LoansAvailable> findByLoanType(String loanType);
}
