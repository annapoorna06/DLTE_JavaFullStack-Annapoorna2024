package project.backend;

import mybank.dao.interfaces.LoansInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project.backend.mvc.LoansUiController;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class LoansUiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoansInterface loansInterface;

    @InjectMocks
    private LoansUiController loansUiController;

    @Test
    public void testLandingPage() {
        String result = loansUiController.landing();
        assertEquals("index", result);
    }

    @Test
    public void testDashboard() {
        String result = loansUiController.dashboard();
        assertEquals("dashboard", result);
    }

    @Test
    public void testViewAll(){
        String result=loansUiController.viewAll();
        assertEquals("viewAll",result);
    }

    @Test
    public void testCalculate(){
        String result=loansUiController.calculate();
        assertEquals("calculate",result);
    }



}