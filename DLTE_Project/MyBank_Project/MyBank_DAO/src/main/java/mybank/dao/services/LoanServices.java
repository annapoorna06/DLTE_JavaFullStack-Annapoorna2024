package mybank.dao.services;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LoanServices implements LoansInterface {
    private static final Logger logger = LoggerFactory.getLogger(LoanServices.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

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
        try {
            List<LoansAvailable> allAvailLoan = jdbcTemplate.query("select * from mybank_app_loanavailable", new LoanAvailableMapper());
            if (allAvailLoan == null) {
                throw new NoLoanDataException();
            } else {
                logger.info("All available loans retrieved successfully: {}", allAvailLoan);
            }
            return allAvailLoan;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all available loans: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<LoansAvailable> findByLoanType(String loanType) {
        //filter using query
        try {
            String sql = "SELECT * FROM MYBANK_APP_LOANAVAILABLE WHERE LOAN_TYPE = ?";
            List<LoansAvailable> loansByType = jdbcTemplate.query(sql, new Object[]{loanType}, new LoanAvailableMapper());
            if (loansByType == null || loansByType.isEmpty()) {
                throw new NoLoanDataException();
            } else {
                logger.info("Loans retrieved successfully for loan type {}: {}", loanType, loansByType);
            }
            return loansByType;
        } catch (NoLoanDataException e) {
            logger.warn("No loans found for the specified loan type: {}", loanType);
        } catch (Exception e) {
            logger.error("Error occurred while fetching loans by type: {}", e.getMessage());
        }
        return null;

        //filter using stream(for Java 8 implementation)
//        try {
//            String sql = "SELECT * FROM MYBANK_APP_LOANAVAILABLE";
//            List<LoansAvailable> allLoans = jdbcTemplate.query(sql, new LoanAvailableMapper());
//
//            if (allLoans == null || allLoans.isEmpty()) {
//                throw new NoLoanDataException();
//            }
//
//            List<LoansAvailable> loansByType = allLoans.stream()
//                    .filter(loan -> loan.getLoanType().equalsIgnoreCase(loanType))
//                    .collect(Collectors.toList());
//
//            if (loansByType.isEmpty()) {
//                logger.warn("No loans found for the specified loan type: {}", loanType);
//            } else {
//                logger.info("Loans retrieved successfully for loan type {}: {}", loanType, loansByType);
//            }
//
//            return loansByType;
//        } catch (NoLoanDataException e) {
//            logger.warn("No loans found for the specified loan type: {}", loanType);
//        } catch (Exception e) {
//            logger.error("Error occurred while fetching loans by type: {}", e.getMessage());
//        }
//        return null;
    }
}

