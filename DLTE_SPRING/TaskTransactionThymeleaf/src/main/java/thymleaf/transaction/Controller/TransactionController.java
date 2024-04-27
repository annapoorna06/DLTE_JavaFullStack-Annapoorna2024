package thymleaf.transaction.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thymleaf.transaction.Entity.Transactions;
import thymleaf.transaction.Exceptions.TransactionException;
import thymleaf.transaction.Services.TransactionsService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionsService transactionService;
    //for new transaction
    @PostMapping("/new")
    public Transactions saved(@RequestBody Transactions transactions){
        Transactions transactions1=null;
        try{
            transactions1=transactionService.apiInsert(transactions);

        }catch (Exception exp){
            System.out.println(exp);
        }
        return transactions1;

    }
    //find by sender's name
//    @GetMapping("/findBySender/{senderName}")
//    public List<Transactions> getSender(@PathVariable String senderName){
//        List<Transactions> transactionName=null;
//        try{
//            transactionName=transactionService.apiBySender(senderName);
//        }catch (TransactionException exp){
//            System.out.println(exp);
//        }
//        return transactionName;
//    }
//    //find by receiver's name
//    @GetMapping("/findByReciever/{receiverName}")
//    public List<Transactions> getReciever(@PathVariable String receiverName){
//        List<Transactions> transactionName=null;
//        try{
//            transactionName=transactionService.apiByRecever(receiverName);
//        }catch (TransactionException exp){
//            System.out.println(exp);
//        }
//        return transactionName;
//    }
//    //find by amount
//    @GetMapping("/findByAmount/{amount}")
//    public List<Transactions> getAmount(@PathVariable Long amount){
//        List<Transactions> transactionName=null;
//        try{
//            transactionName=transactionService.apiByAmount(amount);
//        }catch (TransactionException exp){
//            System.out.println(exp);
//        }
//        return transactionName;
//    }

}