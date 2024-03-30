package jdbc.soapweb;

import jdbc.soapweb.dao.Transactions;
import jdbc.soapweb.dao.TransactionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class Xsdsoapwebservice1154ApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransactionsService transactionService;

    private List<Transactions> initialTransactions() {
        List<Transactions> newList = new ArrayList<>();
        Transactions transactions1 = new Transactions(1, new Date(2024, 03, 24), "Annapoorna", 1000, "Family", "Bharathi");
        Transactions transactions2 = new Transactions(2, new Date(2024, 03, 24), "Udaya", 8000, "Friends", "Bharathi");
        newList.add(transactions1);
        newList.add(transactions2);
        return newList;
    }

    @Test
    void testGetByAmount() {
        Transactions transactions1 = new Transactions(1, new Date(2024, 03, 24), "Annapoorna", 1000, "Family", "Bharathi");
        Transactions transactions2 = new Transactions(2, new Date(2024, 03, 24), "Udaya", 8000, "Friends", "Bharathi");

        List<Transactions> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), (ResultSetExtractor<Object>) any())).thenReturn(transactionsList);

        List<Transactions> actualOne = transactionService.findByAmount(8000.0);
        assertEquals(transactionsList, actualOne);
    }

    @Test
    void testUpdateRemarks() {
        List<Transactions> test = initialTransactions();
        when(jdbcTemplate.query(any(String.class), any(Object[].class), (ResultSetExtractor<Object>) any())).thenReturn(test);
        Transactions result = transactionService.updateRemarks(test.get(0));
        assertEquals(test.get(1), result);
    }

    @Test
    void testBySender() {
        Transactions transactions1 = new Transactions(1, new Date(2024, 03, 24), "Annapoorna", 1000, "Family", "Bharathi");
        Transactions transactions2 = new Transactions(2, new Date(2024, 03, 24), "Udaya", 8000, "Friends", "Bharathi");

        List<Transactions> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), (ResultSetExtractor<Object>) any())).thenReturn(transactionsList);

        List<Transactions> result = transactionService.findBySender("Bharathi");
        assertNotNull(result);
        assertEquals(transactionsList, result);
    }

    @Test
    void testRemoveTransactionBetweenDates() {
        Date startDate = new Date(2024, 03, 29);
        Date endDate = new Date(2024, 04, 29);
        when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);
        String result = transactionService.deleteTransaction(startDate, endDate);
        assertEquals("Transaction deleted", result);
    }

    @Test
    void testByReceiver() {
        Transactions transactions1 = new Transactions(1, new Date(2024, 03, 24), "Annapoorna", 1000, "Family", "Bharathi");
        Transactions transactions2 = new Transactions(2, new Date(2024, 03, 24), "Udaya", 8000, "Friends", "Bharathi");

        List<Transactions> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), (ResultSetExtractor<Object>) any())).thenReturn(transactionsList);

        List<Transactions> result = transactionService.findByReceiver("Udaya");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testNewTransaction() {
        Transactions transaction = new Transactions(1, new Date(2024, 03, 24), "Annapoorna", 1000, "Family", "Bharathi");
        when(jdbcTemplate.update(any(String.class), (Object) any())).thenReturn(1);
        Transactions result = transactionService.newTransaction(transaction);
        assertNotNull(result);
        assertEquals(transaction, result);
    }
}
