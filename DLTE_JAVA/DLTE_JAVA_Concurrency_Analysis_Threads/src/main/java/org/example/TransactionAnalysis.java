package org.example;


import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis implements Runnable {
        Transaction[] transaction = {
                new Transaction(new Date(2021, 8, 24), 500, "Akshatha", "Friend"),
                new Transaction(new Date(2020, 9, 12), 5000, "Prajeet", "Family"),
                new Transaction(new Date(2022, 8, 03), 6500, "Annapoorna", "Education"),
                new Transaction(new Date(2024, 5, 15), 7000, "Akshay", "Bills"),
                new Transaction(new Date(2021, 8, 24), 50000, "Raksha", "Friend"),
        };
        public  void run() {

            TransactionAnalysis customerAnalyis = new TransactionAnalysis();
            int choice;
            Scanner scanner = new Scanner(System.in);
            //menu for transaction analysis
            while(true) {
                System.out.println("----------TRANSACTION ANALYSIS------------");
                System.out.println("1 - least amount transferred\n" +
                        "2 - transaction to particular beneficiary\n" +
                        "3 - filtering based on particular remark\n" +
                        "4 - sort in descending based on beneficiary \n" +
                        "5 - sort based on amount transferred ascending\n" +
                        "6 - identify maximum transaction");
                choice = scanner.nextInt();
                //CreditCardAnalysis analysis = new CreditCardAnalysis();
                switch (choice) {
                    case 1:
                        customerAnalyis.leastAmountTransferred(transaction);
                        return;
                    case 2:
                        customerAnalyis.transactionToParticularBeneficiary(transaction);
                        return;
                    case 3:
                        customerAnalyis.FilteringBasedParticularRemark(transaction);
                        return;
                    case 4:
                        customerAnalyis.SortBeneficiary(transaction);
                        return;
                    case 5:
                        customerAnalyis.SortAmount(transaction);
                        return;
                    case 6:
                        customerAnalyis.MaxAmountTransferred(transaction);
                        return;

                }
            }
        }
        public void displayTransactionToWhom(){
            System.out.println("All the names to whom money is sent");
            for(Transaction each:transaction){
                System.out.println(each.getToWhom());
            }
        }
        public void displayAllRemarks(){
            System.out.println("All the remarks in transaction");
            for(Transaction each: transaction){
                System.out.println(each.getRemarks());
            }
        }
        public void displayAllAmount(){
            System.out.println("All the amount transefered");
            for(Transaction each: transaction){
                System.out.println(each.getAmountInTransaction());
            }
        }
        //range of transaction based on dates
        public void RangeBasedOnDates(Transaction[] transaction){
            String StartDateInput;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter start range date dd/mm/yyyy format");
            StartDateInput=scanner.next();
            String splitStartDate[]=StartDateInput.split("/");
            for(Transaction each: transaction){
                if((Integer.parseInt(splitStartDate[0])==(each.getDateOfTransaction()).getDate())&&(Integer.parseInt(splitStartDate[1])==(each.getDateOfTransaction()).getMonth())&&(Integer.parseInt(splitStartDate[2])==(each.getDateOfTransaction()).getYear())) {
                    System.out.println("Transaction on date "+(each.getDateOfTransaction()).getDate()+"to"+each.getToWhom());
                    System.out.println(each.getAmountInTransaction());
                }

            }

        }
        //least money transferred by the person
        public void leastAmountTransferred(Transaction[] transaction){
            int leastAmounts=Integer.MAX_VALUE;
            for(Transaction each:transaction){
                int compareAmount=each.getAmountInTransaction();
                if(compareAmount<leastAmounts){
                    leastAmounts=compareAmount;
                }
            }
            System.out.println("The least amount transfered is "+leastAmounts);
        }

        //maximum money transferred by the person
        public void MaxAmountTransferred(Transaction[] transaction){
            int MaxAmounts=Integer.MIN_VALUE;
            for(Transaction each:transaction){
                int compareAmount=each.getAmountInTransaction();
                if(compareAmount>MaxAmounts){
                    MaxAmounts=compareAmount;
                }
            }
            System.out.println("The maximum amount transfered is "+MaxAmounts);
        }
        //transaction to particular beneficiary
        public void transactionToParticularBeneficiary(Transaction[] transaction){
            Scanner scanner=new Scanner(System.in);
            String Beneficiary;
            System.out.println("Enter beneficiary name");
            Beneficiary= scanner.next();
            int NumberOfTransaction=0;
            for(Transaction each:transaction){
                if(each.getToWhom().equals(Beneficiary)){
                    NumberOfTransaction++;
                }

            }
            System.out.println("Number of transaction to "+Beneficiary+"= "+NumberOfTransaction);
        }
        //filtering based on particular remark in transaction
        public void FilteringBasedParticularRemark(Transaction[] transaction){
            Scanner scanner=new Scanner(System.in);
            String Remark;
            System.out.println("Enter Remark");
            Remark= scanner.next();
            for(Transaction each:transaction){
                if(each.getRemarks().equals(Remark)){
                    System.out.println(each.getAmountInTransaction()+" is the amount transferred to "+each.getToWhom()+" has remark "+Remark);
                }

            }

        }
        //selection sort based on Beneficiary in descending
        public void SortBeneficiary(Transaction[] transaction){
            Transaction temporary=null;
            for(int index=0;index<transaction.length-1;index++){
                int maximumIndex=index;
                String maximumString=transaction[index].getToWhom();
                for(int next=index+1;next<transaction.length;next++){
                    if(transaction[next].getToWhom().compareTo(maximumString)>0){
                        maximumString=transaction[next].getToWhom();
                        maximumIndex=next;
                    }
                }
                if(maximumIndex!=index){
                    temporary=transaction[maximumIndex];
                    transaction[maximumIndex]=transaction[index];
                    transaction[index]=temporary;
                }
            }
        }
        //selection sort based on amount in ascending
        public void SortAmount(Transaction[] transaction){
            Transaction temporary=null;
            for(int index=0;index<transaction.length-1;index++){
                int minimumIndex=index;
                int minimumAmount=transaction[index].getAmountInTransaction();
                for(int next=index+1;next<transaction.length;next++){
                    if(transaction[next].getAmountInTransaction()<minimumAmount){
                        minimumAmount=transaction[next].getAmountInTransaction();
                        minimumIndex=next;
                    }
                }
                if(minimumIndex!=index){
                    temporary=transaction[minimumIndex];
                    transaction[minimumIndex]=transaction[index];
                    transaction[index]=temporary;
                }
            }
        }

    }
