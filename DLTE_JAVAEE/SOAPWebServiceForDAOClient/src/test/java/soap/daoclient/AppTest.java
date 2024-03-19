package soap.daoclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import soap.daoclient.dao.FetchAccount;
import soap.daoclient.dao.SOAPService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Mock
    private SOAPService soapService;

    @Mock
    private FetchAccount fetchAccount;

    @Before
    public void setUp() {
        when(soapService.getSOAPServicePort()).thenReturn(fetchAccount);
    }

    @Test
    public void testCreateUser() {
        // Mocking user input
        String username = "annapoornapai";
        String password = "anna";
        String dateOfBirth = "2002-07-06";
        String address = "Karkala";
        String email = "anna@gmail.com";
        long phoneNumber = 6363276256L;

        // Mocking service response
        doNothing().when(fetchAccount).create(username, password, dateOfBirth, address, email, phoneNumber);

        // Verifying service method invocation
        verify(fetchAccount).create(username, password, dateOfBirth, address, email, phoneNumber);
    }

    @Test
    public void testFindUser() {
        // Mocking user input
        String username = "annapoornapai";

        // Mocking service response
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Verifying service method invocation and output
        verify(fetchAccount).findUser(username);
        assertEquals("Username: " + username + " Password: null", getConsoleOutput());
    }

    @Test
    public void update() {
        // Mocking user input
        String username = "annapoornapai";

        // Mocking service response
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Verifying service method invocation and output
        verify(fetchAccount).findUser(username);
    }
    @Test
    public void testUpdateUser() {
        // Mocking user input
        String username = "testUser";

        // Mocking service response for finding user
        List<FetchAccount> accounts = new ArrayList<>();
        FetchAccount account = new FetchAccount();
        account.setuserName(username);
        accounts.add(account);
        when(fetchAccount.findUser(username)).thenReturn(fetchAccount);
        when(fetchAccount.getuserDetailsList()).thenReturn(accounts);

        // Mocking service response for update process
        String newPassword = "annap@12";
        String newAddress = "karkala";
        String newEmail = "annap@gmail.com";
        Long newPhoneNumber = 9876543210L;

        verify(fetchAccount).findUser(username);
        verify(fetchAccount).update(username, newPassword, new Date, newAddress, newEmail, newPhoneNumber);
    }


    // Helper method to capture console output
    private String getConsoleOutput() {
        return null; // Implement capturing console output if necessary
    }
}
