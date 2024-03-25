package spring.jdbc.template;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.jdbc.template.Entity.Transactions;
import spring.jdbc.template.service.TransactionService;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SpringJdbctemplateApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransactionService transactionService;

//    @Test
//    void testNewTransaction() {
//        // testing for adding transactions
//        Transactions transaction1 = new Transactions(101, Date.valueOf("2024-03-26"),"Akshatha", "Annapoorna",1001.0, "Friends");
//        when(jdbcTemplate.update(
//                eq("insert into transaction values(?,?,?,?,?,?)"), any(Long.class), any(Date.class),any(String.class), any(String.class), any(Integer.class), any(String.class)
//        )).thenReturn(1);
//        Transactions transactionActual = transactionService.newTransaction(transaction1);
//        assertEquals(transaction1.getTransactionBy(), transactionActual.getTransactionBy());
//    }

    @Test
    void testFindBySender() {
        // test for finding transactions by sender's name
        Transactions transaction1 = new Transactions(101, Date.valueOf("2024-03-26"),"Akshatha", "Annapoorna",1001.0, "Friends");
        Transactions transaction2 = new Transactions(102, Date.valueOf("2024-03-28"), "Raksha", "Arundhathi", 200, "Bills");
        List<Transactions> expected = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findBySender("Raksha");
        assertNotEquals(expected, actual);
    }

    @Test
    void testFindByReceiver() {
        // testing using receiver's name
        Transactions transaction1 = new Transactions(104, Date.valueOf("2024-03-26"), "Prajeeth", "Annapoorna", 2000, "Family");
        Transactions transaction2 = new Transactions(106, Date.valueOf("2024-03-28"), "Udaya", "Bharathi", 40000, "Emergency");
        List<Transactions> expected = Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findByReceiver("Udaya");
        assertNotEquals(expected, actual);
    }

    @Test
    void testFindByAmount() {
        //testing using entering the amount
        Transactions transaction1 = new Transactions(107, Date.valueOf("2024-03-26"), "Annapoorna", "Prajeet", 7000, "Family");
        Transactions transaction2 = new Transactions(108, Date.valueOf("2024-03-28"), "Mithali", "Arundhathi", 5000, "Bills");
        List<Transactions> expected = Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findByAmount((long) 1200);
        assertNotEquals(expected, actual);
    }
}
