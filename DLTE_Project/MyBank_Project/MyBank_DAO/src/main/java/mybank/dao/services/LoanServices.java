package mybank.dao.services;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public List<LoansAvailable> findByLoanType(String loanType) {
//        try {
//            // Call the PL/SQL procedure to read loans by type
//            String sql = "{call read_loans_by_type(?, ?, ?, ?, ?)}";
//            List<LoansAvailable> loansByType = jdbcTemplate.query(sql, new Object[]{loanType, null, null, null, null}, new LoanAvailableMapper());
//            if (loansByType == null || loansByType.isEmpty()) {
//                throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//            }
//            return loansByType;
//        } catch (NoLoanDataException e) {
//            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//        } catch (LoanServiceException e) {
//            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
//        }

            try {
                // Call the PL/SQL procedure to read loans by type
                String sql = "{call read_loans_by_type(?, ?, ?, ?, ?)}";
                List<LoansAvailable> loansByType = jdbcTemplate.query(sql, new Object[]{loanType, null, null, null, null}, new LoanAvailableMapper());

                // Filter loans by loanType using Java Stream
                List<LoansAvailable> filteredLoans = loansByType.stream()
                        .filter(loan -> loan.getLoanType().equalsIgnoreCase(loanType))
                        .collect(Collectors.toList());

                if (filteredLoans.isEmpty()) {
                    throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
                }

                return filteredLoans;
            } catch (NoLoanDataException e) {
                throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
            } catch (LoanServiceException e) {
                throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
            }
        }

    @Override
    public double getRateOfInterestByLoanType(String loanType) {
        try {
            // Call the PL/SQL procedure to read loans by type
            String sql = "SELECT loan_roi FROM mybank_app_loanavailable WHERE loan_type = ?";
            Double rateOfInterest = jdbcTemplate.queryForObject(sql, new Object[]{loanType}, Double.class);

            if (rateOfInterest == null) {
                // Handle the case when no rate of interest is found for the given loan type
                throw new NoLoanDataException( resourceBundle.getString("no.roi") + loanType);
            }
            return rateOfInterest;
        }  catch (NoLoanDataException e) {
            // throw NoLoanDataException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
        } catch (LoanServiceException e) {
            // throw LoanServiceException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
        }
    }


}



