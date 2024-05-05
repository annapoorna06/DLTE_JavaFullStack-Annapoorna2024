package mybank.dao.interfaces;

import mybank.dao.entity.LoansAvailable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
//interface for methods
@Repository
public interface LoansInterface {
    public List<LoansAvailable> allAvailableLoans();
    //public List<LoansAvailable> findByLoanType(String loanType) throws SQLException;
    public double getRateOfInterestByLoanName(String loanName);
}
