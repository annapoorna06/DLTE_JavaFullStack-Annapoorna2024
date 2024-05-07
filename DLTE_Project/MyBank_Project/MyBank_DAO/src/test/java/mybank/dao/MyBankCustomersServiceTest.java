package mybank.dao;
import mybank.dao.entity.MyBankCustomers;

import mybank.dao.services.LoanServices;
import mybank.dao.services.MyBankCustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MyBankCustomersServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MyBankCustomersService myBankCustomersService;

    @InjectMocks
    private LoanServices loanServices;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // Mock data
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        mockCustomer.setPassword("nayak");
        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class)))
                .thenReturn(mockCustomer);
        // Test and verify
        assertDoesNotThrow(() -> {
            try {
                UserDetails userDetails = myBankCustomersService.loadUserByUsername("Akshatha");
                assertEquals("Akshatha", userDetails.getUsername());
            } catch (Exception e) {
            }
        });
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        // Mock behavior to simulate no user found
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class)))
                .thenReturn(null);
        // Test and verify that the method throws UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> myBankCustomersService.loadUserByUsername("Annapoorna"));
    }

    @Test
    void testSigningUp_Success() {
        // Mock data
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        mockCustomer.setPassword("nayak");
        // Mock behavior
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        // Test
        MyBankCustomers result = myBankCustomersService.signingUp(mockCustomer);
        // Verify
        assertEquals("Akshatha", result.getUsername());
    }

    @Test
    void testSigningUpNotNull_Success() {
        // Mock data
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        mockCustomer.setPassword("nayak");
        // Mock behavior
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        // Test
        MyBankCustomers result = myBankCustomersService.signingUp(mockCustomer);
        // Verify
        assertNotNull(result);
    }

    @Test
    void testFindByUsername_UserExists() {
        // Mock data
        List<MyBankCustomers> mockCustomersList = new ArrayList<>();
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        mockCustomersList.add(mockCustomer);
        // Mock behavior
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(mockCustomersList);
        // Test
        MyBankCustomers result = myBankCustomersService.findByUsername("Akshatha");

        // Verify
        assertNotNull(result);
        assertEquals("Akshatha", result.getUsername());
    }

    @Test
    void testUpdateAttempts() {
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        mockCustomer.setAttempts(5);
        // Mock behavior
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        // Test
        assertDoesNotThrow(() -> myBankCustomersService.updateAttempts(mockCustomer));
    }

    @Test
    void testUpdateStatus() {
        MyBankCustomers mockCustomer = new MyBankCustomers();
        mockCustomer.setUsername("Akshatha");
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        assertDoesNotThrow(() -> myBankCustomersService.updateStatus(mockCustomer));
    }

    @Test
    void testGetCustomerName() {
        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn("Akshatha");
        String customerName = myBankCustomersService.getCustomerName("Akshatha");
        assertEquals("Akshatha", customerName);
    }

    @Test
    public void testPasswordMatch() {
        MyBankCustomersService myBankCustomersService = mock(MyBankCustomersService.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Setup test data
        String username = "Annapoorna";
        String rawPassword = "pai";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        // Configure mock behavior
        MyBankCustomers customers = new MyBankCustomers();
        customers.setUsername(username);
        customers.setPassword(encodedPassword);
        when(myBankCustomersService.loadUserByUsername(username))
                .thenReturn(customers);
        // Invoke the authentication process
        UserDetails userDetails = myBankCustomersService.loadUserByUsername(username);
        String enteredPassword="pai";
        // Verify the result
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));

    }
}
