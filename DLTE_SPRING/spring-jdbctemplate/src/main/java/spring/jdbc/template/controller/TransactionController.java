package spring.jdbc.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.jdbc.template.Entity.Transactions;
import spring.jdbc.template.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions") // Updated endpoint to lowercase
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired // Added autowired annotation to inject TransactionService dependency
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add") // Simplified endpoint
    public Transactions newTransaction(@RequestBody Transactions transactions) {
        return transactionService.newTransaction(transactions);
    }

    @GetMapping("/sender/{sender}")
    public List<Transactions> findBySender(@PathVariable String sender) {
        return transactionService.findBySender(sender);
    }

    @GetMapping("/receiver/{receiver}")
    public List<Transactions> findByReceiver(@PathVariable String receiver) {
        return transactionService.findByReceiver(receiver);
    }

    @GetMapping("/amount/{amount}")
    public List<Transactions> findByAmount(@PathVariable Long amount) {
        return transactionService.findByAmount(amount);
    }
}
