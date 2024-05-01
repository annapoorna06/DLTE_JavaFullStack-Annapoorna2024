package project.backend.rest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loans")
public class LoanServicesController {

    Logger logger = LoggerFactory.getLogger(LoanServices.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("apps");
    @Autowired
    private LoansInterface loanService;
    //http://localhost:8083/loans/type/{LoanType}
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All data fetched"),
            @ApiResponse(responseCode = "204", description = "No loans found for the specified loan type:"),
            @ApiResponse(responseCode = "404", description = "No Loan Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

//    @GetMapping("/type/{loanType}")
//    public ResponseEntity<List<LoansAvailable>> findByLoanType(@PathVariable String loanType, HttpServletResponse response) {
//        try {
//            if (!isValidLoanType(loanType)) {
//                logger.warn(resourceBundle.getString("enter.proper.loantype"));
//                return ResponseEntity.badRequest().body(Collections.emptyList());
//            }
//            List<LoansAvailable> loans = loanService.findByLoanType(loanType);
//            if (loans == null || loans.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            } else {
//                response.setStatus(HttpServletResponse.SC_OK);
//                logger.info(resourceBundle.getString("loan.server.available"));
//                return ResponseEntity.ok(loans);
//            }
//        } catch (NoLoanDataException e) {
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//            logger.error(resourceBundle.getString("no.loanType"), e);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
//        } catch (LoanServiceException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            logger.error(resourceBundle.getString("db.error"), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
//        }
//    }

    private boolean isValidLoanType(String loanType){
        return loanType != null && !loanType.isEmpty() && loanType.matches("[A-Za-z]+");
    }

    @GetMapping("/name/{loanName}")
    public String getRoiByLoanName(@PathVariable String loanName, HttpServletResponse response) {
        try {
            double rateOfInterest = loanService.getRateOfInterestByLoanName(loanName);
            return String.valueOf(rateOfInterest);
        } catch (NoLoanDataException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(resourceBundle.getString("loan.name.not.found"));
            return resourceBundle.getString("loan.name.not.found") + loanName;
        } catch (LoanServiceException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(resourceBundle.getString("db.error"));
            return resourceBundle.getString("db.error");
        }
    }


    @GetMapping("/emi/{loanName}/")
    public String calculateEMI(@PathVariable String loanName,
                               @RequestParam double amount,
                               @RequestParam int tenure,
                               HttpServletResponse response) {
        try {
            if (amount > 0 && tenure > 0) {
                double rateOfInterest;
                try {
                    rateOfInterest = Double.parseDouble(getRoiByLoanName(loanName, response));
                } catch (NumberFormatException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    logger.error(resourceBundle.getString("invalid.rate.of.interest"));
                    return resourceBundle.getString("invalid.rate.of.interest");
                }
                double monthlyInterest = rateOfInterest / (12 * 100);
                double emi = (amount * monthlyInterest * Math.pow(1 + monthlyInterest, tenure)) / (Math.pow(1 + monthlyInterest, tenure) - 1);
                return resourceBundle.getString("emi.pay")+ " is:" + emi;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return resourceBundle.getString("no.negative.zero");
            }
        } catch (NoLoanDataException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return resourceBundle.getString("loan.name.not.found");
        } catch (DataAccessException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(resourceBundle.getString("emi.calculation.error"));
            return resourceBundle.getString("emi.calculation.error");
        }
    }

}


//rest API document-http://localhost:8083/v3/api-docs