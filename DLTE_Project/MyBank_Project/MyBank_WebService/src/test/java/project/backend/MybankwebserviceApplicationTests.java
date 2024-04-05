package project.backend;

import mybank.dao.entity.LoansAvailable;
import mybank.dao.interfaces.LoansInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.backend.configuration.SoapPhase;
import services.loans.ViewAllAvailableLoanRequest;
import services.loans.ViewAllAvailableLoanResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MybankwebserviceApplicationTests {

    @Autowired
    private SoapPhase loanPhase;
    @MockBean
    private LoansInterface interfaceServices;
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
        //test based on response
        assertNotNull(response);
        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
        //number of loans list passed
        assertNotNull(response.getLoanAvailable());
        assertEquals(5, response.getLoanAvailable().size());
    }


}
