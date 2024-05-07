package project.backend;
import mybank.dao.entity.MyBankCustomers;
import mybank.dao.services.MyBankCustomersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import project.backend.rest.LoanServicesController;
import project.backend.security.CustomersFailureHandler;
import project.backend.security.CustomersSuccesshandler;
import project.backend.security.MyBankCustomersApi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest

@AutoConfigureMockMvc

class SecurityTesting  {
    @Mock
    JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;
    @InjectMocks
    private CustomersFailureHandler customersFailureHandler;
    @InjectMocks
    private CustomersSuccesshandler customersSuccesshandler;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @Mock
    private AuthenticationException exception;
    @Mock
    private MyBankCustomersService myBankCustomersService;
    @InjectMocks
    private MyBankCustomersApi myBankCustomersApi;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private LoanServicesController loanServicesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void onAuthenticationFailureTest() throws Exception {
        customersFailureHandler.setUseForward(true);
        MyBankCustomers customers = new MyBankCustomers();
        customers.setCustomerId(123);
        customers.setCustomerName("divija");
        customers.setCustomerAddress("ujire");
        customers.setCustomerStatus("active");
        customers.setCustomerContact(8277263396L);
        customers.setUsername("user");
        customers.setPassword("1234");
        customers.setAttempts(1);
        when(myBankCustomersService.findByUsername("user")).thenReturn(customers);
        // Mocking the request parameters
        mockMvc.perform(MockMvcRequestBuilders.post("/weblogin/")
                .param("user", customers.getUsername())
                .param("1234", customers.getPassword()))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/weblogin/?error=Wrong username entered."));
    }


    @Test
    public void testSuccessHandler() throws  IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        MyBankCustomers customers = new MyBankCustomers();
        customers.setCustomerStatus("Inactive"); // Assuming status allows authentication
        when(authentication.getPrincipal()).thenReturn(customers);

        customersSuccesshandler.onAuthenticationSuccess(request, response, authentication);

        assertEquals("/weblogin/?errors=Account suspended contact admin to redeem!", response.getRedirectedUrl());
    }
    @Test
    public void save() {
        // Mock data
        MyBankCustomers customers = new MyBankCustomers();
        customers.setUsername("User");
        customers.setPassword("Pass");
        when(passwordEncoder.encode(customers.getPassword())).thenReturn("encodedPassword");
        when(myBankCustomersService.signingUp(customers)).thenReturn(customers);
        MyBankCustomers savedCustomer = myBankCustomersApi.save(customers);
        verify(passwordEncoder).encode("Pass");
        verify(myBankCustomersService).signingUp(customers);
        assertEquals("User", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming getPassword() returns the encoded password
    }


}