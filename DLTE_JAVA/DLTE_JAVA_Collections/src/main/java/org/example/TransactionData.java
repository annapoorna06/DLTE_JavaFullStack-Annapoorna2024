package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

public class TransactionData {
    private List<Transaction> transactions;

    public TransactionData() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> filterByDateRange(String startDate, String endDate) {
        return transactions.stream().filter(transaction -> transaction.getDateOfTransaction().compareTo(startDate) >= 0 &&
                transaction.getDateOfTransaction().compareTo(endDate) <= 0).collect(Collectors.toList());
    }
//use of method references
    public Transaction findLeastAmountTransaction() {
        return transactions.stream().min(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
    }

    public Transaction findMaximumAmountTransaction() {
        return transactions.stream().max(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
    }

    public List<Transaction> customSort(Comparator<Transaction> comparator) {
        return transactions.stream().sorted(comparator).collect(Collectors.toList());
    }
}

