package spring.queries.remotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.queries.model.Transactions;

import java.util.List;

public interface TranactionRemote extends JpaRepository<Transactions, Integer> {
    //for sql(native) query
    @Query(value = "select * from transactions where user_name=:user and transaction_type=:type",nativeQuery = true)
    List<Transactions> findByUserAndType(String user, String type);
    //for hql query
    @Query("from Transactions where transactionAmount between :amount1 and :amount2")
    List<Transactions> findByRangeOfTransactionAmount(double amount1, double amount2);
}
