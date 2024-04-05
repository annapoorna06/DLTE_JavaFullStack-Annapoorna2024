package mybank.dao.services;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class LoanServices implements LoansInterface {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(LoanServices.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    //gets loan from db and maps to it's respective data
    public class LoanAvailableMapper implements RowMapper<LoansAvailable> {
        @Override
        public LoansAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            LoansAvailable loanAvailable = new LoansAvailable();
            loanAvailable.setLoanNumber(rs.getInt(1));
            loanAvailable.setLoanType(rs.getString(2));
            loanAvailable.setLoanName(rs.getString(3));
            loanAvailable.setLoanDescription(rs.getString(4));
            loanAvailable.setLoanRoi(rs.getDouble(5));
            return loanAvailable;
        }
    }

    //soap web service implementation to get all the loans
    @Override
    public List<LoansAvailable> allAvailableLoans() {
        List<LoansAvailable> allAvailLoan;
        try {
             allAvailLoan = jdbcTemplate.query("select * from mybank_app_loanavailable", new LoanAvailableMapper());
            if (allAvailLoan == null) {
                throw new NoLoanDataException(resourceBundle.getString("no.loans"));
            }
        }//if any error encountered interms of database
        catch (DataAccessException dao) {
            logger.error(resourceBundle.getString("db.error"));
            throw new LoanServiceException(resourceBundle.getString("no.service.exp"));
        }//if any nullPointerException
        if(allAvailLoan.size()==0){
            logger.warn(resourceBundle.getString("no.loans"));
            throw new NoLoanDataException(resourceBundle.getString("no.loans"));
        }
        return allAvailLoan;
    }
    //rest service
    @Override
    public List<LoansAvailable> findByLoanType(String loanType) {
        try {
            String sql = "SELECT * FROM MYBANK_APP_LOANAVAILABLE WHERE LOAN_TYPE = ?";
            List<LoansAvailable> loansByType = jdbcTemplate.query(sql, new Object[]{loanType}, new LoanAvailableMapper());
            if (loansByType == null || loansByType.isEmpty()) {
                logger.warn(resourceBundle.getString("no.loanType"));
                throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
            }
            return loansByType;
        } catch (NoLoanDataException e) {
            logger.warn(resourceBundle.getString("no.loanType"));
            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("error.loanType"));
            throw new NoLoanDataException(resourceBundle.getString("error.loanType") + e.getMessage());
        }
    }


        //filter using stream(for Java 8 implementation)
//        try {
//            String sql = "SELECT * FROM MYBANK_APP_LOANAVAILABLE";
//            List<LoansAvailable> allLoans = jdbcTemplate.query(sql, new LoanAvailableMapper());
//
//            if (allLoans == null || allLoans.isEmpty()) {
//                throw new NoLoanDataException(resourceBundle.getString("error.noLoansFound"));
//            }
//
//            List<LoansAvailable> loansByType = allLoans.stream()
//                    .filter(loan -> loan.getLoanType().equalsIgnoreCase(loanType))
//                    .collect(Collectors.toList());
//
//            if (loansByType.isEmpty()) {
//                throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//            }
//
//            return loansByType;
//        } catch (NoLoanDataException e) {
//            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//        } catch (Exception e) {
//            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
//        }
//
//    }
}


