package spring.queries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.queries.model.Transactions;
import spring.queries.remotes.TranactionRemote;
import spring.queries.services.TransactionServices;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
public class TransactionAPI {
    @Autowired
    TransactionServices transactionServices;
    //New Transaction of POST mapping as XML request
    @PostMapping(value = "/create" ,consumes = "application/xml",produces = "application/xml")
    public Transactions callNewTransaction(@RequestBody Transactions transactions){
        return transactionServices.newTransactions(transactions);
    }
    //GetMapping using Native SQL query
    @GetMapping("/findByUserAndType/{name}/{type}")
    public List<Transactions> callFindTransactions(@PathVariable("name") String name,@PathVariable("type") String type){
        return transactionServices.findAllByUserAndType(name, type);
    }
    //TransactionAmount by using HQL
    @GetMapping("/findByAmountRange/{amount1}/{amount2}")
    public List<Transactions> callFindByAmountRange(@PathVariable("amount1") double amount1, @PathVariable("amount2") double amount2){
        return transactionServices.findAllByRange(amount1,amount2);
    }
}

