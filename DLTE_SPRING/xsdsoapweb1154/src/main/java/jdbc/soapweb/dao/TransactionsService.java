package jdbc.soapweb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TransactionsService {
        @Autowired
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
        public List<Transactions> findByAmount(Double amount) {
            String sql = "SELECT * FROM transaction WHERE transaction_amount = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), amount);
        }
        //updating remarks of transactions
        public Transactions updateRemarks(Transactions transactions){
            int acknowledge=jdbcTemplate.update("update transaction set transaction_remarks=? where transaction_id=?",
                    new Object[]{transactions.getTransactionRemarks(),transactions.getTransactionId()}
            );
            if(acknowledge!=0) return transactions;
            else  return null;
        }
        //deleting transactions between start date and end date
        public String deleteTransaction(Date startDate, Date endDate){
            int acknowledge= jdbcTemplate.update("delete from transaction where transaction_date between ? and ?",
                    new Object[]{startDate,endDate}
            );
            if(acknowledge!=0) return "Transaction deleted";
            else return "Failed to delete transaction";
        }
    }

