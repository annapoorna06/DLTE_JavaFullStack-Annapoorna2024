package org.example;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionAnalyzer implements Runnable {
        Lock lock=new ReentrantLock();
            Transaction[] myBank = {
                    new Transaction(new Date(2024, 02, 20), 1000, "Aru", "Friends"),
                    new Transaction(new Date(2024, 02, 19), 500, "Prajeet", "Family"),
                    new Transaction(new Date(2024, 02, 18), 100, "Hospital", "Emergency"),
                    new Transaction(new Date(2024, 02, 17), 700, "MITE", "Education"),
                    new Transaction(new Date(2024, 02, 16), 2000, "Electricity board", "BIlls")
            };
    public  void run() {
        lock.lock();
            TransactionAnalyzer analyze = new TransactionAnalyzer();
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.Filter based on date range");
            System.out.println("2.Find the least amount transferred");
            System.out.println("3.Find the maximum amount transferred");
            System.out.println("4.Find the number of transaction made to particular beneficiary");
            System.out.println("5.Filter based on particular remarks");
            System.out.println("6.Sort based on beneficiary in descending order");
            System.out.println("7.Sort based on amount in ascending order");
            System.out.println("8.Exit");
            System.out.println("Enter your option");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    analyze.filterByDateRange(myBank);
                    break;
                case 2:
                    analyze.leastAmountTransferred(myBank);
                    break;
                case 3:
                    analyze.maxAmountTransferred(myBank);
                    break;
                case 4:
                    analyze.countTransactionToBenificiary(myBank);
                    break;
                case 5:
                    analyze.filterOnRemarks(myBank);
                    break;
                case 6:
                    analyze.sortDescendingByBeneficiary(myBank);
                    break;
            }

        }

        //Filter based on date range
        public void filterByDateRange(Transaction[] transactions) {
            Scanner scanner = new Scanner(System.in);
            String startDate;
            System.out.println("Enter the date in dd/mm/yyyy format");
            startDate = scanner.next();
            String splitstartDate[] = startDate.split("/");
            for (Transaction each : transactions) {
                if (Integer.parseInt(splitstartDate[0]) == (each.getDateOfTransaction().getDate()) && Integer.parseInt(splitstartDate[1]) == (each.getDateOfTransaction().getMonth()) && Integer.parseInt(splitstartDate[2]) == (each.getDateOfTransaction().getYear())) {
                    System.out.println("Transaction on date " + (each.getDateOfTransaction()).getDate() + " to " + each.getTransactionTo());
                }
            }
        }

        //Find the least amount transferred
        public void leastAmountTransferred(Transaction[] transactions) {
            Transaction minTransaction = transactions[0];
            for (int index = 1; index < transactions.length; index++) {
                if (transactions[index].getAmountInTransaction() < minTransaction.getAmountInTransaction())
                    minTransaction = transactions[index];
            }
            System.out.println("The least amount transferred is " + minTransaction.getAmountInTransaction());
        }

        //Find the maximum amount transferred
        public void maxAmountTransferred(Transaction[] transactions) {
            Transaction maxTransaction = transactions[0];
            for (int index = 1; index < transactions.length; index++) {
                if (transactions[index].getAmountInTransaction() > maxTransaction.getAmountInTransaction())
                    maxTransaction = transactions[index];
            }
            System.out.println("The maximum amount transferred is " + maxTransaction.getAmountInTransaction());
        }

        //Find the number of transaction made to particular beneficiary
        public void countTransactionToBenificiary(Transaction[] transactions) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter beneficiary name: ");
            String beneficiaryName = scanner.nextLine();
            int count = 0;
            for (int index = 0; index < transactions.length; index++) {
                if (transactions[index].getTransactionTo().equalsIgnoreCase(beneficiaryName)) {
                    count++;
                }
            }
            System.out.println("Number of transactions made to " + beneficiaryName + " are " + count);
        }

        //Filter based on particular remarks
        public void filterOnRemarks(Transaction[] transactions) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter certain remark: ");
            String remarks = scanner.nextLine();
            for (int index = 0; index < transactions.length; index++) {
                if (transactions[index].getRemarks().equalsIgnoreCase(remarks)) {
                    System.out.println("The transaction is made to " + transactions[index].getTransactionTo() + " on " + transactions[index].getDateOfTransaction() + " based on remarks");
                }
            }
        }

        //Sort based on beneficiary in descending order
        public void sortDescendingByBeneficiary(Transaction[] transactions) {
            Transaction backup = null;
            for (int select = 0; select < transactions.length - 1; select++) {
                for (int next = 0; next < transactions.length - select - 1; next++) {
                    if (transactions[select].getTransactionTo().compareTo(transactions[next].getTransactionTo()) < 0) {
                        backup = transactions[select];
                        transactions[select] = transactions[next];
                        transactions[next] = backup;
                    }
                }
                for (Transaction each : transactions) {
                    System.out.println(each.getTransactionTo());
                }

            }

        }

        //Sort based on amount in ascending order
        public void sortAscendingByAmount(Transaction[] transactions) {
            Transaction backup = null;
            for (int select = 0; select < transactions.length - 1; select++) {
                for (int next = 0; next < transactions.length - select - 1; next++) {
                    if (transactions[select].getAmountInTransaction() > transactions[next].getAmountInTransaction()) {
                        backup = transactions[select];
                        transactions[select] = transactions[next];
                        transactions[next] = backup;
                    }
                }
                for (Transaction each : transactions) {
                    System.out.println(each.getTransactionTo());
                }

            }

        }
    }