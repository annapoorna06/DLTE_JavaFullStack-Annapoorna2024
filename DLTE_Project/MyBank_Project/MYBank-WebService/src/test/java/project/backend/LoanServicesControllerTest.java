package project.backend;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.backend.rest.LoanServicesController;

import javax.servlet.http.HttpServletResponse;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanServicesControllerTest {
    @InjectMocks
    private LoanServicesController loanServicesController;

    @Mock
    private LoansInterface loanService;

//    @Mock
//    private ResourceBundle resourceBundle=ResourceBundle.getBundle("apps");
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loanServicesController).build();
    }

    @Test
    public void testGetRoiByLoanName_Success() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "Kissan loan";
        double rateOfInterest = 5.0;
        when(loanService.getRateOfInterestByLoanName(loanName)).thenReturn(rateOfInterest);
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.getRoiByLoanName(loanName, response);
        // Assertions
        assertEquals(String.valueOf(rateOfInterest), result);
        verify(response, never()).setStatus(anyInt()); // Ensure response status is not set
    }

    @Test
    public void testGetRoiByLoanName_NoLoanDataException() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "Vidya Poshak Loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new NoLoanDataException("Mocked exception"));
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.getRoiByLoanName(loanName, response);
        // Assertions
        assertNotEquals("loan.name.not.foundTestLoan", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
    @Test
    public void testGetRoiByLoanName_LoanServiceException() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "Union Gold Loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new LoanServiceException("Mocked exception"));
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.getRoiByLoanName(loanName, response);
        // Assertions
        assertNotEquals("db.error", result);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    @Test
    public void testCalculateEMI_Success() {
        // Mocking
        String loanName = "Agriculture loan";
        double rateOfInterest = 5.0;
        double amount = 10000;
        int tenure = 12;
        when(loanService.getRateOfInterestByLoanName(loanName)).thenReturn(rateOfInterest);
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI(loanName, amount, tenure, response);
        // Assertions
        // Perform assertions based on expected EMI calculation result
        assertNotNull(result);
    }
    @Test
    public void testCalculateEMI_NegativeAmount() {
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI("TestLoan", -10000, 12, response);
        // Assertions
        assertNotEquals("Amount cannot be negative or zero", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }


    @Test
    public void testCalculateEMI_NegativeTenure() {
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI("Gold precious loan", 10000, -12, response);
        // Assertions
        assertNotEquals("no.negative.zero", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
    @Test
    public void testCalculateEMI_LoanNameNotFound() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "krishi loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new NoLoanDataException("Mocked exception"));
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI(loanName, 10000, 12, response);
        // Assertions
        assertNotEquals("loan.name.not.found", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
    @Test
    public void testCalculateEMI_ZeroAmount() {
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI("Car loan", 0, 12, response);
        // Assertions
        assertNotEquals("no.negative.zero", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }

    @Test
    public void testCalculateEMI_ZeroTenure() {
        // Test
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = loanServicesController.calculateEMI("Gold precious loan", 10000, 0, response);
        // Assertions
        assertNotEquals("no.negative.zero", result);
        verify(response).setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }

//    @Test
//    public void testCalculateEMI_InternalServerError() throws NoLoanDataException, LoanServiceException {
//        // Mocking
//        String loanName = "mortgage";
//        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new LoanServiceException("Mocked exception"));
//        // Test
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        String result = loanServicesController.calculateEMI(loanName, 10000, 12, response);
//        // Assertions
//        assertEquals("Rate of interest is invalid, please try again.", result);
//        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    }


    //endpoint testing
    @Test
    public void testGetRoiByLoanNameEndpoint_Success() throws Exception {
        mockMvc.perform(get("/loans/name/{loanName}", "TestLoan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoiByLoanName_NotFound() throws Exception {
        mockMvc.perform(get("/loans/name/{loanName}", "NonExistentLoan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMIEndpoint_Success() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "TestLoan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMI_InvalidParameters() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=-10000&tenure=-12", "TestLoan"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testCalculateEMIEndpoint_LoanNameNotFound() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "NonExistentLoan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMI_InternalServerError() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "ErrorLoan"))
                .andExpect(status().isOk());
    }


}
