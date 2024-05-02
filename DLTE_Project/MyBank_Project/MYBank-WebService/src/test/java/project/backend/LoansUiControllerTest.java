package project.backend;
import mybank.dao.interfaces.LoansInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
public class LoansUiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoansInterface loansInterface;

    @Test
    public void testLanding() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void testDashboard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/dashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/search"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search"));
    }

    @Test
    public void testCalculate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/calculate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("calculate"));
    }

    @Test
    public void testViewAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/viewAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewAll"));
    }

    @Test
    public void testAutowired() {
        assertNotNull(loansInterface);
    }

}
