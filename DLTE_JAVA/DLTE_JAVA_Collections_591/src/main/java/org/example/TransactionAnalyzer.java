package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TransactionAnalyzer {

        public static void main(String[] args) {
            TransactionData transactionData = new TransactionData();

            // Sample transactions examples
            transactionData.addTransaction(new Transaction("2024-03-05", 500.0, "Friend", "Bills"));
            transactionData.addTransaction(new Transaction("2024-02-21", 1000.0, "Family", "Education"));
            transactionData.addTransaction(new Transaction("2024-02-26", 800.0, "Emergency", "Bills"));
            transactionData.addTransaction(new Transaction("2024-02-27", 1200.0, "Bills", "Family"));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                //cli for users
                System.out.println("\n1. Filter transactions by date range");
                System.out.println("2. Find the least amount transaction");
                System.out.println("3. Find the maximum amount transaction");
                System.out.println("4. Customized sort");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        scanner.nextLine();
                        String startDate = scanner.nextLine();
                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDate = scanner.nextLine();
                        List<Transaction> filteredTransactions = transactionData.filterByDateRange(startDate, endDate);
                        System.out.println("Filtered Transactions: " + filteredTransactions);
                        break;
                    case 2:
                        Transaction leastAmountTransaction = transactionData.findLeastAmountTransaction();
                        System.out.println("Least Amount Transaction: " + leastAmountTransaction);
                        break;
                    case 3:
                        Transaction maximumAmountTransaction = transactionData.findMaximumAmountTransaction();
                        System.out.println("Maximum Amount Transaction: " + maximumAmountTransaction);
                        break;
                    case 4:
                        System.out.print("Enter property for sorting (date/amount/to/remarks): ");
                        String property = scanner.nextLine();
                        System.out.print("Enter sorting order (asc/desc): ");
                        String order = scanner.nextLine();
                        Comparator<Transaction> comparator = createComparator(property, order);
                        List<Transaction> sortedTransactions = transactionData.customSort(comparator);
                        System.out.println("Sorted Transactions: " + sortedTransactions);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
        public static Comparator<Transaction> createComparator(String property, String order) {
            Comparator<Transaction> comparator;
            switch (property.toLowerCase()) {
                case "date":
                    comparator = Comparator.comparing(Transaction::getDateOfTransaction);
                    break;
                case "amount":
                    comparator = Comparator.comparing(Transaction::getAmountInTransaction);
                    break;
                case "to":
                    comparator = Comparator.comparing(Transaction::getTo);
                    break;
                case "remarks":
                    comparator = Comparator.comparing(Transaction::getRemarks);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid property for sorting: " + property);
            }
            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            return comparator;
        }
    }


