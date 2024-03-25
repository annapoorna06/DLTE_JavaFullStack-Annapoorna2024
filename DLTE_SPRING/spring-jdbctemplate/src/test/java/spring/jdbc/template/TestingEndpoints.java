package spring.jdbc.template;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import spring.jdbc.template.Entity.Transactions;
import spring.jdbc.template.controller.TransactionController;
import spring.jdbc.template.service.TransactionService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TestingEndpoints {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private TransactionService transactionService;

    @Test
    public void testNewTransactionEndpoint() throws Exception {
//        Transactions transactions;
        Transactions transactions = new Transactions(101 , new Date(2024,03,20),"Annapoorna","Prajeet",1000,"Family");
        String request = "{\"transactionId\": 123456, \"transactionDate\": \"2024-03-25\", \"transactionTo\": \"Receiver\", \"transactionAmount\": 1000, \"transactionRemarks\": \"Test\", \"transactionBy\": \"Sender\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/Transactions/addTransaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testFindBySenderEndpoint() throws Exception {
        Transactions transaction = new Transactions(103 , new Date(2024,03,20),"Annapoorna","Akshatha",1000,"Family");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findBySender("Annapoo")).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sender/Annapoorna")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Annapoorna")); //pass
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Akshatha")); //fail
    }

    @Test
    public void testFindByReceiverEndpoint() throws Exception {
        Transactions transaction = new Transactions(107 , new Date(2024,03,20),"Annapoorna","Prajeet",1000,"Family");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findByReceiver("")).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/receiver/Prajeet")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Prajeet")); //pass
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Annapoorna"));//test fail
    }

    @Test
    public void testFindByAmountENdpoint() throws Exception {
        Transactions transaction = new Transactions(110 , new Date(2024,03,20),"Annapoorna","Prajeet",20000,"Family");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findByAmount(500L)).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/amount/500")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(20000)); //test pass
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(200)); //test fail
    }
}
