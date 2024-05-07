package project.backend;
import mybank.dao.entity.MyBankCustomers;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.interfaces.LoansInterface;
import mybank.dao.services.MyBankCustomersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.backend.mvc.LoansUiController;
import project.backend.rest.LoanServicesController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanServicesControllerTest {
    @InjectMocks
    private LoanServicesController loanServicesController;

    @Mock
    private LoansInterface loanService;

    @Mock
    MyBankCustomersService myBankCustomersService;

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
        String loanName = "TestLoan";
        double rateOfInterest = 5.0;
        when(loanService.getRateOfInterestByLoanName(loanName)).thenReturn(rateOfInterest);
        // Test
        ResponseEntity<String> result = loanServicesController.getRoiByLoanName(loanName);
        // Assertions
        assertEquals(String.valueOf(rateOfInterest), result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void testGetRoiByLoanName_InternalServerError() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "No Loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new LoanServiceException("Mocked exception"));
        // Test
        ResponseEntity<String> result = loanServicesController.getRoiByLoanName(loanName);
        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCalculateEMI_Success() {
        // Mocking
        String loanName = "TestLoan";
        double rateOfInterest = 5.0;
        double amount = 10000;
        int tenure = 12;
        when(loanService.getRateOfInterestByLoanName(loanName)).thenReturn(rateOfInterest);
        // Test
        ResponseEntity<String> result = loanServicesController.calculateEMI(loanName, amount, tenure);
        // Assertions
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCalculateEMI_InvalidParameters() {
        // Test
        ResponseEntity<String> result = loanServicesController.calculateEMI("TestLoan", -10000, -12);
        // Assertions
        assertNotNull(result);
        assertNotEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCalculateEMI_LoanNameNotFound() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "NonExistentLoan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new NoLoanDataException("Mocked exception"));
        // Test
        ResponseEntity<String> result = loanServicesController.calculateEMI(loanName, 10000, 12);
        // Assertions
        assertNotNull(result);
        assertNotEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testCalculateEMI_InternalServerError() throws NoLoanDataException, LoanServiceException {
        // Mocking
        String loanName = "ErrorLoan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new LoanServiceException("Mocked exception"));
        // Test
        ResponseEntity<String> result = loanServicesController.calculateEMI(loanName, 10000, 12);
        // Assertions
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    // Endpoint testing
    @Test
    public void testGetRoiByLoanNameEndpoint_Success() throws Exception {
        mockMvc.perform(get("/loans/loanName/{loanName}", "Gold"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoiByLoanNameEndpoint_NotFound() throws Exception {
        mockMvc.perform(get("/loan/loanName/{loanName}", "variant Loan"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCalculateEMIEndpoint_Success() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "Agriculture"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMIEndpoint_InvalidParameters() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=-10000&tenure=-12", "Vidhya Loan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMIEndpoint_LoanNameNotFound() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "Kisan Loan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateEMIEndpoint_InternalServerError() throws Exception {
        mockMvc.perform(get("/loans/emi/{loanName}/?amount=10000&tenure=12", "djsdsj Loan"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoiByLoanName_WithEmptyResultDataAccessException() {
        // Mocking
        String loanName = "gold Loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new EmptyResultDataAccessException(1));
        // Test
        ResponseEntity<String> response = loanServicesController.getRoiByLoanName(loanName);
        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals("Loan name not found", response.getBody());
    }

    @Test
    public void testGetRoiByLoanName_WithLoanServiceException() {
        // Mocking
        String loanName = "vidya poshak Loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new LoanServiceException("Mocked LoanServiceException"));
        // Test
        ResponseEntity<String> response = loanServicesController.getRoiByLoanName(loanName);
        // Assertions
        assertNotEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void testCalculateEMI_WithNoLoanDataException() {
        // Mocking
        String loanName = "kisan nidhi";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new NoLoanDataException("Mocked NoLoanDataException"));
        // Test
        ResponseEntity<String> response = loanServicesController.calculateEMI(loanName, 10000, 12);
        // Assertions
        assertNotEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void testGetRoiByLoanName_ExistingLoan() {
        // Mocking
        String loanName = "Union Gold Loan";
        double rateOfInterest = 5.0;
        when(loanService.getRateOfInterestByLoanName(loanName)).thenReturn(rateOfInterest);

        // Test
        ResponseEntity<String> response = loanServicesController.getRoiByLoanName(loanName);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("5.0", response.getBody());
    }

    @Test
    public void testGetRoiByLoanName_NonExistingLoan() {
        // Mocking
        String loanName = "car loan";
        when(loanService.getRateOfInterestByLoanName(loanName)).thenThrow(new EmptyResultDataAccessException(1));

        // Test
        ResponseEntity<String> response = loanServicesController.getRoiByLoanName(loanName);
        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals("Loan name not found", response.getBody());
    }

    @Test
    public void testGetCustomerName() {
        // Mock SecurityContextHolder to return a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testUser");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(myBankCustomersService.getCustomerName(Mockito.anyString())).thenReturn("Ann");
        String result = loanServicesController.getCustomerName();
        Assertions.assertEquals("Ann", result);
    }


}
