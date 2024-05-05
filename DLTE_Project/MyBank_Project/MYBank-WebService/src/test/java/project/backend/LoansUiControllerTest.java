package project.backend;
import mybank.dao.interfaces.LoansInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertNotNull;
@SpringBootTest
@AutoConfigureMockMvc
public class LoansUiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoansInterface loansInterface;

    @Test
    @WithMockUser(username = "Akshatha", password = "nayak")
    public void testLanding() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    @WithMockUser(username = "Akshatha", password = "nayak")
    public void testDashboard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/dashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    @WithMockUser(username = "Akshatha", password = "nayak")
    public void testSearch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/search"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search"));
    }

    @Test
    @WithMockUser(username = "Akshatha", password = "nayak")
    public void testCalculate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weblogin/calculate"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Verify unauthorized status
                .andExpect(MockMvcResultMatchers.model().hasNoErrors()) // Add model validation if applicable
                .andExpect(MockMvcResultMatchers.view().name("calculate")); // Change view name to error or appropriate view
    }


    @Test
    @WithMockUser(username = "Akshatha", password = "nayak")
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
