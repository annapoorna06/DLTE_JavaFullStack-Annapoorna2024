package TransactionModule;


import java.util.ArrayList;
import java.util.Date;

public class Transaction {
        private Date dateOfTransaction;
        private Integer amountInTransaction;
        private String toWhom;
        private String remarks;
        private ArrayList<Integer> phoneNumber;
        //constructors
        public Transaction(Date dateOfTransaction, Integer amountInTransaction, String toWhom, String remarks, ArrayList phoneNumber) {
            this.dateOfTransaction = dateOfTransaction;
            this.amountInTransaction = amountInTransaction;
            this.toWhom = toWhom;
            this.remarks = remarks;
            this.phoneNumber= phoneNumber;
        }

        public Transaction() {
        }
        //getters and setters
        public Date getDateOfTransaction() {
            return dateOfTransaction;
        }

        public void setDateOfTransaction(Date dateOfTransaction) {
            this.dateOfTransaction = dateOfTransaction;
        }

        public Integer getAmountInTransaction() {
            return amountInTransaction;
        }

        public void setAmountInTransaction(Integer amountInTransaction) {
            this.amountInTransaction = amountInTransaction;
        }

        public String getToWhom() {
            return toWhom;
        }

        public void setToWhom(String toWhom) {
            this.toWhom = toWhom;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

    public ArrayList getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(ArrayList phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

