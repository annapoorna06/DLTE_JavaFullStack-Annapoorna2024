package org.example;

public class Transaction {
        private String dateOfTransaction;
        private double amountInTransaction;
        private String to;
        private String remarks;

        public Transaction(String dateOfTransaction, double amountInTransaction, String to, String remarks) {
            this.dateOfTransaction = dateOfTransaction;
            this.amountInTransaction = amountInTransaction;
            this.to = to;
            this.remarks = remarks;
        }

        public String getDateOfTransaction() {
            return dateOfTransaction;
        }

        public double getAmountInTransaction() {
            return amountInTransaction;
        }

        public String getTo() {
            return to;
        }

        public String getRemarks() {
            return remarks;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "dateOfTransaction='" + dateOfTransaction + '\'' +
                    ", amountInTransaction=" + amountInTransaction +
                    ", to='" + to + '\'' +
                    ", remarks='" + remarks + '\'' +
                    '}';
        }
    }
