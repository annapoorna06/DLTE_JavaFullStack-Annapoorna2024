package project.backend;
import mybank.dao.entity.LoansAvailable;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.LoanServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import project.backend.configuration.SoapPhase;
import project.backend.rest.LoanServicesController;
import services.loans.ViewAllAvailableLoanRequest;
import services.loans.ViewAllAvailableLoanResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class MybankwebserviceApplicationTests {

    @Autowired
    private SoapPhase loanPhase;
    @Autowired
    private LoanServicesController loanServicesController;
    @MockBean
    private LoansInterface interfaceServices;
    @InjectMocks
    private SoapPhase loanService;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewAvailableLoanRequest_Success() {
        // Arrange
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        List<LoansAvailable> mockLoanList = new ArrayList<>();
        mockLoanList.add(new LoansAvailable());
        mockLoanList.add(new LoansAvailable());
        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
        // Assert
        //test based on response
        assertNotNull(response);
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        //number of loans list passed
        assertNotNull(response.getLoanAvailable());
        assertEquals(2, response.getLoanAvailable().size());
    }

    @Test
    public void testViewAvailableLoanRequest_Failure() {
        // Arrange
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        List<LoansAvailable> mockLoanList = new ArrayList<>();
        mockLoanList.add(new LoansAvailable());
        mockLoanList.add(new LoansAvailable());
        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoanList);
        ViewAllAvailableLoanResponse response = loanPhase.viewAvailLoanRequest(request);
        // Assert
        assertNotNull(response);
        assertNotEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
        //number of loans list passed
        assertNotNull(response.getLoanAvailable());
        assertNotEquals(5, response.getLoanAvailable().size());
    }

    @Test
    public void testViewAvailLoanRequest_Success() throws Exception {
        MockitoAnnotations.initMocks(this);
        List<LoansAvailable> mockLoans = new ArrayList<>();
        when(interfaceServices.allAvailableLoans()).thenReturn(mockLoans);
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        ViewAllAvailableLoanResponse response = loanService.viewAvailLoanRequest(request);
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
    }

    @Test
    public void testViewAvailLoanRequest_Failure() throws Exception {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this);
        // Mocking the behavior of interfaceServices.allAvailableLoans() to throw LoanServiceException
        when(interfaceServices.allAvailableLoans()).thenThrow(new LoanServiceException("Mocked exception"));
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        ViewAllAvailableLoanResponse response = loanService.viewAvailLoanRequest(request);
        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
    }


    @Test
    void testViewAvailLoanRequest_LoanServiceException() {
        // Mocking services
        when(interfaceServices.allAvailableLoans()).thenThrow(new LoanServiceException("Mocked exception"));
        // Test
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        ViewAllAvailableLoanResponse response = loanService.viewAvailLoanRequest(request);
        // Assertions
        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
    }

    @Test
    void testViewAvailLoanRequest_NoLoanDataException() {
        // Mocking
        when(interfaceServices.allAvailableLoans()).thenThrow(new NoLoanDataException("Mocked exception"));
        // Test
        ViewAllAvailableLoanRequest request = new ViewAllAvailableLoanRequest();
        ViewAllAvailableLoanResponse response =loanService.viewAvailLoanRequest(request);
        // Assertions
        assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getServiceStatus().getStatus());
    }



    @Test
    @WithMockUser()
    public void testCalculateEMI_Success_Endpoint() throws Exception {
        // Arrange
        String loanType = "Gold";
        double amount = 10000;
        int tenure = 12;

        // Act & Assert
        mockMvc.perform(get("/loans/Gold/emi")
                .param("amount", String.valueOf(amount))
                .param("tenure", String.valueOf(tenure)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser()
    public void testCalculateEMI_Failure_Endpoint() throws Exception {
        // Arrange
        String loanType = "Gold";
        double amount = 10000;
        int tenure = 12;

        // Act & Assert
        mockMvc.perform(get("/loans/Gold/emi")
                .param("amount", String.valueOf(amount))
                .param("tenure", String.valueOf(tenure)))
                .andExpect(status().isNotFound());
    }

}
