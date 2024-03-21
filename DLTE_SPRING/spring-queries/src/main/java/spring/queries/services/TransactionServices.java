package spring.queries.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.queries.model.Transactions;
import spring.queries.remotes.TranactionRemote;

import java.util.List;

@Service
public class TransactionServices {
    @Autowired
    TranactionRemote tranactionRemote;
    public Transactions newTransactions(Transactions transactions){
        return tranactionRemote.save(transactions);
    }
    public List<Transactions> findAllByUserAndType(String user, String type){
        return tranactionRemote.findByUserAndType(user,type);
    }
    public List<Transactions> findAllByRange(double amount1,double amount2){
        return tranactionRemote.findByRangeOfTransactionAmount(amount1,amount2);
    }

}
