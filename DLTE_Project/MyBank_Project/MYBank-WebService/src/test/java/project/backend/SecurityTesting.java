package project.backend;
import mybank.dao.entity.MyBankCustomers;
import mybank.dao.services.MyBankCustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import project.backend.security.CustomersFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTesting {

    @InjectMocks
    private CustomersFailureHandler customersFailureHandler;

    @Mock
    private MyBankCustomersService myBankCustomersService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authenticationException;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnAuthenticationFailure_ValidUsername_SuccessfulAttempt() throws ServletException, IOException {
        // Mocking
        String username = "testUser";
        MyBankCustomers myBankCustomers = new MyBankCustomers();
        myBankCustomers.setCustomerStatus("Active");
        myBankCustomers.getMaxAttempt();
        myBankCustomers.setAttempts(3);
        when(myBankCustomersService.findByUsername(username)).thenReturn(myBankCustomers);
        // Test
        customersFailureHandler.onAuthenticationFailure(request, response, authenticationException);
        // Verify
        verify(myBankCustomersService).updateAttempts(myBankCustomers);
    }

    @Test
    public void testOnAuthenticationFailure_ValidUsername_MaxAttemptsReached() throws ServletException, IOException {
        // Mocking
        String username = "testUser";
        MyBankCustomers myBankCustomers = new MyBankCustomers();
        myBankCustomers.setCustomerStatus("Active");
        myBankCustomers.getMaxAttempt();
        myBankCustomers.setAttempts(5);
        when(myBankCustomersService.findByUsername(username)).thenReturn(myBankCustomers);
        // Test
        customersFailureHandler.onAuthenticationFailure(request, response, authenticationException);
        // Verify
        verify(myBankCustomersService).updateStatus(myBankCustomers);
    }

    @Test
    public void testOnAuthenticationFailure_InvalidUsername() throws ServletException, IOException {
        // Mocking
        String username = "invalidUser";
        when(myBankCustomersService.findByUsername(username)).thenThrow(new UsernameNotFoundException("Username not found"));
        // Test
        customersFailureHandler.onAuthenticationFailure(request, response, authenticationException);
        // Verify
        verify(response).sendRedirect("/weblogin/?error=incorrect.username");
    }
}
