//package project.backend;
//import mybank.dao.entity.LoansAvailable;
//import mybank.dao.interfaces.LoansInterface;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import project.backend.configuration.SoapPhase;
//import project.backend.rest.LoanServicesController;
//import services.loans.ViewAllAvailableLoanRequest;
//import services.loans.ViewAllAvailableLoanResponse;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//public class MybankwebserviceApplicationTests {
//
//    @Autowired
//    private SoapPhase loanPhase;
//    @Autowired
//    private LoanServicesController loanServicesController;
//    @MockBean
//    private LoansInterface interfaceServices;
//    @Autowired
//    private MockMvc mockMvc;
//    @Test
//    public void testViewAvailableLoanRequest_Success() {
//        // Arrange
//        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable());
//        mockLoanList.add(new LoansAvailable());
//        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
//        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
//        // Assert
//        //test based on response
//        assertNotNull(response);
//        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
//        //number of loans list passed
//        assertNotNull(response.getLoanAvailable());
//        assertEquals(2, response.getLoanAvailable().size());
//    }
//
//    @Test
//    public void testViewAvailableLoanRequest_Failure() {
//        // Arrange
//        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
//        List<LoansAvailable> mockLoanList = new ArrayList<>();
//        mockLoanList.add(new LoansAvailable());
//        mockLoanList.add(new LoansAvailable());
//        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
//        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
//        // Assert
//        //test based on response
//        assertNotNull(response);
//        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
//        //number of loans list passed
//        assertNotNull(response.getLoanAvailable());
//        assertEquals(5, response.getLoanAvailable().size());
//    }
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
//    @WithMockUser(username = "Akshatha")
//    public void testingFindByLoanType_Success() throws Exception {
//        String loanType = "Home"; // Adjust the loan type as needed
//
//        // Act & Assert
//        mockMvc.perform(get("/loans/type/{loanType}", loanType))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @WithMockUser(username = "Akshatha")
//    public void testingFindByLoanType_Failure() throws Exception {
//        String loanType = "Gold";
//        // Act & Assert
//        mockMvc.perform(get("http://localhost:8083/loans/type/{loanType}", loanType))
//                .andExpect(status().isOk());
//    }
//
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
//
//    @Test
//    @WithMockUser()
//    public void testCalculateEMI_Success_Endpoint() throws Exception {
//        // Arrange
//        String loanType = "Gold";
//        double amount = 10000;
//        int tenure = 12;
//
//        // Act & Assert
//        mockMvc.perform(get("/loans/Gold/emi")
//                .param("amount", String.valueOf(amount))
//                .param("tenure", String.valueOf(tenure)))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithMockUser()
//    public void testCalculateEMI_Failure_Endpoint() throws Exception {
//        // Arrange
//        String loanType = "Gold";
//        double amount = 10000;
//        int tenure = 12;
//
//        // Act & Assert
//        mockMvc.perform(get("/loans/Gold/emi")
//                .param("amount", String.valueOf(amount))
//                .param("tenure", String.valueOf(tenure)))
//                .andExpect(status().isOk());
//    }
//
//}
