package org.example;
import service.TransactionByUsername;
import service.TransactionByUsernameService;
import service.Transactions;
import java.util.List;

public class App {
    public static void main(String[] args) {
        TransactionByUsernameService transactionByUsernameService=new TransactionByUsernameService();
        TransactionByUsername transactionByUsername=transactionByUsernameService.getTransactionByUsernamePort();
        List<Transactions> transactions = (List<Transactions>) transactionByUsername.findAllByUsers("annapoornapai");
        for (Transactions each : transactions) {
            System.out.println(each);
        }
    }
}
