package mybank.dao.interfaces;

import mybank.dao.entity.LoansAvailable;
import org.springframework.stereotype.Repository;

import java.util.List;
//interface for methods
@Repository
public interface LoansInterface {
    public List<LoansAvailable> allAvailableLoans();
    public List<LoansAvailable> findByLoanType(String loanType);
    public double getRateOfInterestByLoanType(String loanType);
}
