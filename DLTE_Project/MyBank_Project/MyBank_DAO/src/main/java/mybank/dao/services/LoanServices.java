package mybank.dao.services;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import oracle.jdbc.internal.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Service
public class LoanServices implements LoansInterface {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(LoanServices.class);

    @Autowired
    JdbcTemplate jdbcTemplate;


    //gets loan from db and maps to it's respective data
    public static class LoanAvailableMapper implements RowMapper<LoansAvailable> {
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
            throw new LoanServiceException(resourceBundle.getString("db.error"));
        }catch (RuntimeException ex) {
            logger.error("data.access.error");
            throw new LoanServiceException("data.access.error");
        }//if any nullPointerException
        if(allAvailLoan.size()==0){
            logger.warn(resourceBundle.getString("no.loans"));
            throw new NoLoanDataException(resourceBundle.getString("no.loans"));
        }
        return allAvailLoan;
    }


//    @Override
//    public List<LoansAvailable> findByLoanType(String loanType) {
//        List<LoansAvailable> loansList = new ArrayList<>();
//        try {
//            CallableStatementCreator creator = con -> {
//                CallableStatement statement = con.prepareCall("{call read_loans_by_type(?,?,?)}");
//                statement.setString(1, loanType);
//                statement.registerOutParameter(2, OracleTypes.CURSOR);
//                statement.registerOutParameter(3, Types.VARCHAR);
//                return statement;
//            };
//
//            List<SqlParameter> sqlParameters = Arrays.asList(
//                    new SqlParameter(Types.VARCHAR),
//                    new SqlOutParameter("loans_cursor", OracleTypes.CURSOR),
//                    new SqlOutParameter("loan_info", Types.VARCHAR)
//            );
//
//            Map<String, Object> returnedLoans = jdbcTemplate.call(creator, sqlParameters);
//
//            String loanInfo = (String) returnedLoans.get("loan_info");
//            if (loanInfo != null) {
//                if (loanInfo.equals("NO_LOAN_FOUND")) {
//                    logger.warn(resourceBundle.getString("no.loanType"));
//                    throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//                } else if (loanInfo.startsWith("ERROR")) {
//                    logger.warn(resourceBundle.getString("error.loanType") + loanInfo);
//                    throw new LoanServiceException(resourceBundle.getString("error.loanType") + loanInfo);
//                }
//            }
//
//            ResultSet rs = (ResultSet) returnedLoans.get("loans_cursor");
//            while (rs.next()) {
//                LoansAvailable loan = new LoansAvailable();
//                loan.setLoanNumber(rs.getInt("loan_number"));
//                loan.setLoanType(rs.getString("loan_type"));
//                loan.setLoanName(rs.getString("loan_name"));
//                loan.setLoanDescription(rs.getString("loan_description"));
//                loan.setLoanRoi(rs.getDouble("loan_roi"));
//                loansList.add(loan);
//            }
//            rs.close(); // Close ResultSet after iteration
//        } catch (SQLException ex) {
//            logger.warn(resourceBundle.getString("db.error") + ex.getMessage());
//            throw new LoanServiceException(resourceBundle.getString("db.error") + ex.getMessage());
//        }
//        logger.info(resourceBundle.getString("loan.server.available"));
//        return loansList;
//    }

    @Override
    public double getRateOfInterestByLoanName(String loanName) {
        try {
            // Call the PL/SQL procedure to read loans by loanName
            String sql = "SELECT loan_roi FROM mybank_app_loanavailable WHERE loan_name = ?";
            Double rateOfInterest = jdbcTemplate.queryForObject(sql, new Object[]{loanName}, Double.class);
            if (rateOfInterest == null) {
                // Handle the case when no rate of interest is found for the given loan name
                throw new NoLoanDataException( resourceBundle.getString("no.roi") + loanName);
            }
            return rateOfInterest;
        }  catch (NoLoanDataException e) {
            // throw NoLoanDataException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("no.loanName") + loanName);
        } catch (LoanServiceException e) {
            // throw LoanServiceException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("error.LoanName") + e.getMessage());
        }
    }
}
