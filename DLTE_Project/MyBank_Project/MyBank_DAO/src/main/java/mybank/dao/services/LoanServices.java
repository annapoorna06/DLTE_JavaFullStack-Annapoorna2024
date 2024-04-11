package mybank.dao.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
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

        try {
            List<LoansAvailable> loansList = new ArrayList<>();
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call read_loans_by_type(?,?,?,?,?,?,?)}");
                statement.setString(1, loanType);
                statement.registerOutParameter(2, Types.VARCHAR);
                statement.registerOutParameter(3, Types.VARCHAR);
                statement.registerOutParameter(4, Types.NUMERIC);
                statement.registerOutParameter(5, Types.NUMERIC);
                statement.registerOutParameter(6, Types.VARCHAR);
                statement.registerOutParameter(7, Types.VARCHAR);
                return statement;
            };
            List<SqlParameter> sqlParameters = Arrays.asList(
                    new SqlParameter(Types.VARCHAR),
                    new SqlOutParameter("loan_name", Types.VARCHAR),
                    new SqlOutParameter("loan_description", Types.VARCHAR),
                    new SqlOutParameter("loan_roi", Types.NUMERIC),
                    new SqlOutParameter("loan_number", Types.NUMERIC),
                    new SqlOutParameter("loan_info", Types.VARCHAR),
                    new SqlOutParameter("loan_type_out", Types.VARCHAR)
            );
            Map<String, Object> returnedLoans = jdbcTemplate.call(creator, sqlParameters);

            LoansAvailable loan = new LoansAvailable();
            loan.setLoanNumber(((BigDecimal) returnedLoans.get("loan_number")).intValue());
            loan.setLoanType((String) returnedLoans.get("loan_type_out"));
            loan.setLoanName((String) returnedLoans.get("loan_name"));
            loan.setLoanDescription((String) returnedLoans.get("loan_description"));
            BigDecimal loanRoi = (BigDecimal) returnedLoans.get("loan_roi");
            if (loanRoi != null) {
                loan.setLoanRoi(loanRoi.doubleValue());
            }
            loansList.add(loan);
            return loansList;
        } catch (NoLoanDataException e) {
            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
        } catch (LoanServiceException e) {
            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
        }
    }

        @Override
    public double getRateOfInterestByLoanType(String loanType) {
//        try {
//            // Call the PL/SQL procedure to read loans by type
//            String sql = "SELECT loan_roi FROM mybank_app_loanavailable WHERE loan_type = ?";
//            Double rateOfInterest = jdbcTemplate.queryForObject(sql, new Object[]{loanType}, Double.class);
//
//            if (rateOfInterest == null) {
//                // Handle the case when no rate of interest is found for the given loan type
//                throw new NoLoanDataException( resourceBundle.getString("no.roi") + loanType);
//            }
//            return rateOfInterest;
//        }  catch (NoLoanDataException e) {
//            // throw NoLoanDataException with appropriate message
//            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
//        } catch (LoanServiceException e) {
//            // throw LoanServiceException with appropriate message
//            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
//        }
//    }
        try {
            // Retrieve all loans from the database
            String sql = "SELECT * FROM mybank_app_loanavailable";
            List<LoansAvailable> allLoans = jdbcTemplate.query(sql, new LoanAvailableMapper());

            // Filter loans by loanType using Java Stream
            List<LoansAvailable> loansByType = allLoans.stream()
                    .filter(loan -> loan.getLoanType().equalsIgnoreCase(loanType))
                    .collect(Collectors.toList());

            // If no loans found for the specified loan type, throw exception
            if (loansByType.isEmpty()) {
                throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
            }

            // Assuming only one loan with the specified type exists, retrieve its rate of interest
            double rateOfInterest = loansByType.get(0).getLoanRoi();
            return rateOfInterest;
        } catch (NoLoanDataException e) {
            // throw NoLoanDataException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("no.loanType") + loanType);
        } catch (LoanServiceException e) {
            // throw LoanServiceException with appropriate message
            throw new NoLoanDataException(resourceBundle.getString("error.LoanType") + e.getMessage());
        }
    }

}


//     procedure included:
//    CREATE OR REPLACE PROCEDURE read_loans_by_type (
//        loan_type       IN VARCHAR2,
//        loan_name       OUT VARCHAR2,
//        loan_description OUT VARCHAR2,
//        loan_roi        OUT NUMBER,
//        loan_number     OUT NUMBER,
//        loan_info       OUT VARCHAR2,
//        loan_type_out   OUT VARCHAR2 -- Additional output parameter for loan_type
//) AS
//    BEGIN
//            SELECT
//        loan_name, loan_description, loan_roi, loan_number, loan_type
//                INTO
//                loan_name, loan_description, loan_roi, loan_number,loan_type_out
//                FROM
//                mybank_app_loanavailable
//                WHERE
//                loan_type = read_loans_by_type.loan_type;
//                loan_info := 'Loan details fetched successfully';
//                EXCEPTION
//                WHEN NO_DATA_FOUND THEN
//                loan_info := 'No loans found for the specified loan type';
//                WHEN OTHERS THEN
//                loan_info := 'Error occurred: ' || SQLERRM;
//                END;
//                /
//
//                VARIABLE loan_name VARCHAR2(255)
//                VARIABLE loan_description VARCHAR2(4000)
//                VARIABLE loan_roi NUMBER
//                VARIABLE loan_number NUMBER
//                VARIABLE loan_type_out VARCHAR2(255)
//                VARIABLE loan_info VARCHAR2(100)
//                -- Add variable for loan_type_out
//
//                EXECUTE read_loans_by_type('Agriculture', :loan_name, :loan_description, :loan_roi, :loan_number, :loan_info, :loan_type_out); -- Include loan_type_out in the EXECUTE statement



