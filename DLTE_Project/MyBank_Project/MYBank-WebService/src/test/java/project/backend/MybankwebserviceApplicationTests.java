//package project.backend;
//
//import mybank.dao.entity.LoansAvailable;
//import mybank.dao.exceptions.LoanServiceException;
//import mybank.dao.exceptions.NoLoanDataException;
//import mybank.dao.interfaces.LoansInterface;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.dao.DataAccessException;
//import project.backend.configuration.SoapPhase;
//import project.backend.rest.LoanServicesController;
//import services.loans.ViewAllAvailableLoanRequest;
//import services.loans.ViewAllAvailableLoanResponse;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class MybankwebserviceApplicationTests {
//
//    @Autowired
//    private SoapPhase loanPhase;
//    @Autowired
//    private LoanServicesController loanServicesController;
//    @MockBean
//    private LoansInterface interfaceServices;
////    @Test
////    public void testViewAvailableLoanRequest_Success() {
////        // Arrange
////        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
////        List<LoansAvailable> mockLoanList = new ArrayList<>();
////        mockLoanList.add(new LoansAvailable());
////        mockLoanList.add(new LoansAvailable());
////        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
////        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
////        // Assert
////        //test based on response
////        assertNotNull(response);
////        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
////        //number of loans list passed
////        assertNotNull(response.getLoanAvailable());
////        assertEquals(2, response.getLoanAvailable().size());
////    }
////
////    @Test
////    public void testViewAvailableLoanRequest_Failure() {
////        // Arrange
////        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
////        List<LoansAvailable> mockLoanList = new ArrayList<>();
////        mockLoanList.add(new LoansAvailable());
////        mockLoanList.add(new LoansAvailable());
////        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
////        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
////        // Assert
////        //test based on response
////        assertNotNull(response);
////        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
////        //number of loans list passed
////        assertNotNull(response.getLoanAvailable());
////        assertEquals(5, response.getLoanAvailable().size());
////    }
//    @Test
//    public void testFindByLoanType_Success() {
//        // Arrange
//        String loanType = "home";
//        HttpServletResponse response = null;
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable());
//        when(interfaceServices.findByLoanType(loanType)).thenReturn(mockLoanList);
//
//        // Act
//        List<LoansAvailable> result = interfaceServices.findByLoanType(loanType);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testFindByLoanType_Failure() {
//        // Arrange
//        String loanType = "home";
//        HttpServletResponse response = null;
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable());
//        mockLoanList.add(new LoansAvailable());
//        when(interfaceServices.findByLoanType(loanType)).thenReturn(mockLoanList);
//        // Act
//        List<LoansAvailable> result = interfaceServices.findByLoanType(loanType);
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testCalculateEMI_Success() {
//        // Arrange
//        String loanType = "home_loan";
//        double amount = 10000;
//        int tenure = 12;
//        HttpServletResponse response = null;
//        double expectedEMI = 1000.0; // Assuming a fixed EMI for testing purposes=actual value
//        when(interfaceServices.getRateOfInterestByLoanType(loanType)).thenReturn(10.0); // Assuming 10% rate of interest
//        // Act
//        double result = loanServicesController.calculateEMI(loanType, amount, tenure, response);
//        // Assert
//        assertEquals(expectedEMI, result);
//    }
//    @Test
//    public void testCalculateEMI_Failure() {
//        // Arrange
//        String loanType = "home_loan";
//        double amount = 10000;
//        int tenure = 12;
//        HttpServletResponse response = null;
//        double expectedEMI = 2000.0; // Incorrect expected EMI value actusl value=879
//        when(interfaceServices.getRateOfInterestByLoanType(loanType)).thenReturn(10.0); // Assuming 10% rate of interest
//
//        // Act
//        double result = loanServicesController.calculateEMI(loanType, amount, tenure, response);
//
//        // Assert
//        assertEquals(expectedEMI, result); // The test should fail as the expected EMI doesn't match the calculated EMI
//    }
//
//}
