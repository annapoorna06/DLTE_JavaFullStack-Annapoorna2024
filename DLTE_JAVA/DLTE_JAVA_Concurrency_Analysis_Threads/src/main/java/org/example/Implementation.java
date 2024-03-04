package org.example;

public class Implementation {
        public static void main(String[] args) throws InterruptedException {
            TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
            Thread thread1=new Thread(transactionAnalysis,"ThreadOne");
            thread1.start();thread1.join();
            Thread thread2=new Thread(transactionAnalysis,"ThreadTwo");
            thread2.start();
            Thread thread3=new Thread(transactionAnalysis,"ThreadThree");
            thread3.start();

            Thread thread4=new Thread(transactionAnalysis::displayTransactionToWhom,"ThreadFour");
            thread4.start();
            Thread thread5=new Thread(transactionAnalysis::displayAllRemarks,"ThreadFive");
            thread5.start();
            Thread thread6=new Thread(transactionAnalysis::displayAllAmount,"ThreadSix");
            thread6.start();
            Thread thread7 =new Thread(transactionAnalysis,"ThreadSeven");
            thread7.start();
            Thread thread8=new Thread(transactionAnalysis,"ThreadEight");
            thread8.start();
            Thread thread9=new Thread(transactionAnalysis,"ThreadNine");
            thread9.start();
            Thread thread10=new Thread(transactionAnalysis,"ThreadTen");
            thread10.start();

        }
    }
