package project.backend.rest;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.LoanServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/loans")
public class LoanServicesController {

    Logger logger = LoggerFactory.getLogger(LoanServices.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private LoansInterface loanService;
    //http://localhost:8082/loans/{LoanType}
//    @GetMapping("/loans/{loanType}")
//    public List<LoansAvailable> findByLoanType(@PathVariable String loanType, HttpServletResponse response) throws LoanServiceException {
//        try {
//            List<LoansAvailable> loans = loanService.findByLoanType(loanType);
//            if (loans.isEmpty()) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                logger.warn(resourceBundle.getString("no.loan.type"), loanType);
//                throw new NoLoanDataException(resourceBundle.getString("no.loan.type") + loanType);
//            }
//            return loans;
//        } catch (NoLoanDataException e) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            logger.error(resourceBundle.getString("no.loan.type"), loanType, e);
//            throw e;
//        } catch (DataAccessException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            logger.error(resourceBundle.getString("loan.server.error"), e);
//            throw new LoanServiceException(resourceBundle.getString("loan.server.error"));
//        }
//    }


    @GetMapping("/findByLoanType/{loanType}")
    public ResponseEntity<List<LoansAvailable>> findByLoanType(@PathVariable String loanType) {
        try {
            List<LoansAvailable> loans = loanService.findByLoanType(loanType);
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } catch (NoLoanDataException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (LoanServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/{loanType}/emi")
    public double calculateEMI(@PathVariable String loanType,
                               @RequestParam double amount,
                               @RequestParam int tenure,
                               HttpServletResponse response) throws LoanServiceException {
       // http://localhost:8082/loans/Gold/emi?amount=10000&tenure=12
        try {
            // Retrieve rate of interest from database based on loan type
            double rateOfInterest = loanService.getRateOfInterestByLoanType(loanType);
            // Convert rate of interest to decimal
            double monthlyInterest = rateOfInterest / (12 * 100);
            // Calculate EMI
            double emi = (amount * monthlyInterest * Math.pow(1 + monthlyInterest, tenure)) / (Math.pow(1 + monthlyInterest, tenure) - 1);
            //return resourceBundle.getString("emi.pay") + loanType + " is:" + emi;
            return emi;
        } catch (DataAccessException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(resourceBundle.getString("emi.calculation.error"));
            throw new LoanServiceException(resourceBundle.getString("emi.calculation.error"));
        }
    }


}
