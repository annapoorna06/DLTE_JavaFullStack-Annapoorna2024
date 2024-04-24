//package project.backend.rest;
//import mybank.dao.entity.LoansAvailable;
//import mybank.dao.exceptions.LoanServiceException;
//import mybank.dao.exceptions.NoLoanDataException;
//import mybank.dao.interfaces.LoansInterface;
//import mybank.dao.services.LoanServices;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.ResourceBundle;
//
//@RestController
//@RequestMapping("/loans")
//public class LoanServicesController {
//
//    Logger logger = LoggerFactory.getLogger(LoanServices.class);
//    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    @Autowired
//    private LoansInterface loanService;
//    //http://localhost:8082/loans/{LoanType}
//    @GetMapping("/{loanType}")
//    public ResponseEntity<Object> findByLoanType(@PathVariable String loanType, HttpServletResponse response) {
//        try {
//            List<LoansAvailable> loans = loanService.findByLoanType(loanType);
//            if (loans.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            } else {
//                return ResponseEntity.ok(loans);
//            }
//        } catch (NoLoanDataException e) {
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);//204 no content
//            logger.error(resourceBundle.getString("no.loanType"), e);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (LoanServiceException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500 no content
//            logger.error(resourceBundle.getString("db.error"), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{loanType}/emi")
//    public double calculateEMI(@PathVariable String loanType,
//                               @RequestParam double amount,
//                               @RequestParam int tenure,
//                               HttpServletResponse response) throws LoanServiceException {
//       // http://localhost:8082/loans/Gold/emi?amount=10000&tenure=12
//        try {
//            // Retrieve rate of interest from database based on loan type
//            double rateOfInterest = loanService.getRateOfInterestByLoanType(loanType);
//            // Convert rate of interest to decimal
//            double monthlyInterest = rateOfInterest / (12 * 100);
//            // Calculate EMI
//            double emi = (amount * monthlyInterest * Math.pow(1 + monthlyInterest, tenure)) / (Math.pow(1 + monthlyInterest, tenure) - 1);
//            //return resourceBundle.getString("emi.pay") + loanType + " is:" + emi;
//            return emi;
//        } catch (DataAccessException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500
//            logger.error(resourceBundle.getString("emi.calculation.error"));
//            throw new LoanServiceException(resourceBundle.getString("emi.calculation.error"));
//        }
//    }
//}
//
//
////rest API document-http://localhost:8082/v3/api-docs