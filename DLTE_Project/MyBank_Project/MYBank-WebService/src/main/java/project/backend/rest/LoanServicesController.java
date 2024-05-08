package project.backend.rest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.LoanServices;
import mybank.dao.services.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loans")
public class LoanServicesController {
    @Autowired
    MyBankCustomersService myBankCustomersService;
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

    @GetMapping("/loanName/{loanName}")
    public ResponseEntity<String> getRoiByLoanName(@PathVariable String loanName) {
        try {
            double rateOfInterest = loanService.getRateOfInterestByLoanName(loanName);
            return ResponseEntity.ok(String.valueOf(rateOfInterest));
        } catch (EmptyResultDataAccessException e) {
            logger.error(resourceBundle.getString("loan.name.not.found"));
            logger.error(resourceBundle.getString("error.one")+resourceBundle.getString("loan.name.not.found"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.one")+resourceBundle.getString("loan.name.not.found")); // Return 404 NOT FOUND status// Return 404 NOT FOUND status
        } catch (LoanServiceException e) {
            logger.error(resourceBundle.getString("error.two")+resourceBundle.getString("db.error"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.two")+resourceBundle.getString("db.error"));
        }
    }

    @GetMapping("/emi/{loanName}/")
    public ResponseEntity<String> calculateEMI(@PathVariable String loanName,
                                               @RequestParam double amount,
                                               @RequestParam int tenure) {
        try {
            if (amount > 0 && tenure > 0) {
                ResponseEntity<String> roiResponse = getRoiByLoanName(loanName);
                if (roiResponse.getStatusCode() == HttpStatus.OK) {
                    double rateOfInterest = Double.parseDouble(roiResponse.getBody());
                    double monthlyInterest = (rateOfInterest / 100) / 12;
                    double emi = (amount * monthlyInterest * Math.pow(1 + monthlyInterest, tenure)) / (Math.pow(1 + monthlyInterest, tenure) - 1);
                    String formattedEMI = String.format("%.3f", emi);
                    logger.info(resourceBundle.getString("result.emi"));
                    return ResponseEntity.ok(resourceBundle.getString("emi.pay") + formattedEMI);
                } else {
                    return ResponseEntity.status(roiResponse.getStatusCode())
                            .body(roiResponse.getBody());
                }
            } else {
                logger.warn(resourceBundle.getString("no.negative.zero"));
                logger.error(resourceBundle.getString("error.six")+resourceBundle.getString("no.negative.zero"));

                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.six")+resourceBundle.getString("no.negative.zero"));
            }
        } catch (NoLoanDataException e) {
            logger.warn(resourceBundle.getString("error.three")+resourceBundle.getString("loan.name.not.found"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.three")+resourceBundle.getString("loan.name.not.found"));
        } catch (NumberFormatException ex) {
            logger.warn(resourceBundle.getString("error.four")+resourceBundle.getString("invalid.rate.of.interest"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.four")+resourceBundle.getString("invalid.rate.of.interest"));
        } catch (DataAccessException e) {
            logger.warn(resourceBundle.getString("error.five")+resourceBundle.getString("emi.calculation.error"));
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("error.five")+resourceBundle.getString("emi.calculation.error"));
        }
    }


    @GetMapping("/name")
    public String getCustomerName() {
        String name = getUser();
        String user = myBankCustomersService.getCustomerName(name);
        return user;
    }
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return name;
    }
}


//rest API document-http://localhost:8083/v3/api-docs