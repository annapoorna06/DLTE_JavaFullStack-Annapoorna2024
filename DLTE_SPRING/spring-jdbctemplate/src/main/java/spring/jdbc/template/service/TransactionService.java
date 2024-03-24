package spring.jdbc.template.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import spring.jdbc.template.Entity.Transactions;

import java.util.List;

@Service
public class TransactionService {
    private JdbcTemplate jdbcTemplate;
    //inserting into transaction table
    public Transactions newTransaction(Transactions transactions){
        String sql = "INSERT INTO transaction (transaction_id, transaction_date, transaction_to, transaction_amount,transaction_remarks, transaction_by) VALUES (?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, transactions.getTransactionId(), transactions.getTransactionDate(), transactions.getTransactionTo(), transactions.getTransactionAmount(),transactions.getTransactionRemarks(), transactions.getTransactionBy());
        return transactions;
    }
    //finding the list of transactions by transaction sender(transaction_by)
    public List<Transactions> findBySender(String sender) {
        String sql = "SELECT * FROM transaction WHERE transaction_by = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), sender);
    }
    //finding the list of transactions by receiver(transaction_to)
    public List<Transactions> findByReceiver(String receiver) {
        String sql = "SELECT * FROM transaction WHERE transaction_to = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), receiver);
    }
    //finding list of transactions by entering amount(transaction_amount)
    public List<Transactions> findByAmount(Long amount) {
        String sql = "SELECT * FROM transaction WHERE transaction_amount = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), amount);
    }
}
