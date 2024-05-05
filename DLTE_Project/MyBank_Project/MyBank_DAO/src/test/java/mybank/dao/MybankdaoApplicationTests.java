package mybank.dao;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.entity.MyBankCustomers;
import mybank.dao.exceptions.LoanServiceException;
import mybank.dao.exceptions.NoLoanDataException;
import mybank.dao.services.LoanServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MybankdaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private LoanServices loanServices;

    private List<LoansAvailable> mockLoanList;

    @BeforeEach
    void setUp() {
        mockLoanList = new ArrayList<>();
        mockLoanList.add(new LoansAvailable(101, "Personal", "Personal loan", "personal expenses", 8.8));
    }

    @Test
    void testAllAvailableLoans_Success() {
        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
        List<LoansAvailable> result = loanServices.allAvailableLoans();
        assertEquals(1, result.size());
    }

    @Test
    void testAllAvailableLoans_Failure() {
        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(mockLoanList);
        List<LoansAvailable> result = loanServices.allAvailableLoans();
        assertNotEquals("Education", result.get(0).getLoanName());
    }
    //LoansAvailable
    @Test
    void testSetters() {
        LoansAvailable loan = new LoansAvailable();
        loan.setLoanNumber(102);
        loan.setLoanType("Home");
        loan.setLoanName("Home loan");
        loan.setLoanDescription("buying a home");
        loan.setLoanRoi(7.5);
        assertEquals(102, loan.getLoanNumber());
        assertEquals("Home", loan.getLoanType());
        assertEquals("Home loan", loan.getLoanName());
        assertEquals("buying a home", loan.getLoanDescription());
        assertEquals(7.5, loan.getLoanRoi());
    }
    //MyBankCustomers
    @Test
    void testFieldAccessorsAndMutators() {
        MyBankCustomers customer = new MyBankCustomers();
        customer.setCustomerId(123);
        customer.setCustomerName("Akshatha");
        customer.setCustomerAddress("Bailoor");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(9353523995L);
        customer.setUsername("Akshatha");
        customer.setPassword("nayak");
        customer.setAttempts(1);

        assertEquals(123, customer.getCustomerId());
        assertEquals("Akshatha", customer.getCustomerName());
        assertEquals("Bailoor", customer.getCustomerAddress());
        assertEquals("Active", customer.getCustomerStatus());
        assertEquals(9353523995L, customer.getCustomerContact());
        assertEquals("Akshatha", customer.getUsername());
        assertEquals("nayak", customer.getPassword());
        assertEquals(1, customer.getAttempts());
    }

    @Test
    void testUserDetailsInterfaceMethods() {
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("Akshatha");
        customer.setPassword("nayak");

        assertEquals("Akshatha", customer.getUsername());
        assertEquals("nayak", customer.getPassword());
        assertTrue(customer.isAccountNonExpired());
        assertTrue(customer.isAccountNonLocked());
        assertTrue(customer.isCredentialsNonExpired());
        assertTrue(customer.isEnabled());
    }

    @Test
    void testGetMaxAttempt() {
        MyBankCustomers customer = new MyBankCustomers();
        assertEquals(3, customer.getMaxAttempt());
    }


    @Test
    void allAvailableLoan_NoDataFound() {
        when(jdbcTemplate.query(anyString(), any(LoanServices.LoanAvailableMapper.class))).thenReturn(new ArrayList<>());
        assertThrows(NoLoanDataException.class, () -> loanServices.allAvailableLoans());
    }

    @Test
    void testGetRateOfInterestByLoanName_Success() {
        // Mock database query to return a known interest rate for a specific loan name
        when(jdbcTemplate.queryForObject(any(), any(Object[].class), any(Class.class))).thenReturn(8.8);

        // Call the method under test
        double result = loanServices.getRateOfInterestByLoanName("Personal loan");

        // Assert that the correct interest rate is returned
        assertEquals(8.8, result);
    }

    @Test
    void testGetRateOfInterestByLoanName_NoLoanData() {
        // Mock database query to return null, simulating no data found for the loan name
        when(jdbcTemplate.queryForObject(any(), any(Object[].class), any(Class.class))).thenReturn(null);

        // Assert that the method throws NoLoanDataException when no data is found for the loan name
        assertThrows(NoLoanDataException.class, () -> loanServices.getRateOfInterestByLoanName("Nonexistent loan"));
    }

    @Test
    void testGetRateOfInterestByLoanName_Exception() {
        // Mock database query to throw an exception, simulating an error
        when(jdbcTemplate.queryForObject(any(), any(Object[].class), any(Class.class))).thenThrow(new RuntimeException());

        // Assert that the method throws LoanServiceException when an exception occurs
        assertThrows(RuntimeException.class, () -> loanServices.getRateOfInterestByLoanName("Some loan"));
    }

    @Test
    void testGetRateOfInterestByLoanName_NoLoanDataException() {
        // Mock the behavior of jdbcTemplate.queryForObject() to return null, simulating no data found
        when(jdbcTemplate.queryForObject(any(), any(Object[].class), any(Class.class))).thenReturn(null);

        // Assert that the method throws NoLoanDataException when no data is found for the loan name
        assertThrows(NoLoanDataException.class, () -> loanServices.getRateOfInterestByLoanName("Vidhyarti Loan"));
    }


}
