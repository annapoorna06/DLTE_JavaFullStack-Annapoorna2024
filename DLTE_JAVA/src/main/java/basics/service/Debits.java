package basics.service;

import java.util.Scanner;

public class Debits {
    public static void main(String[] args) {
        int DebitNo=0;
        double amount, initialAmount;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the initial amount: ");
        initialAmount=scanner.nextDouble();
        for(int transactions=1;transactions<10;transactions++){
            System.out.println("Enter amount of transaction "+ transactions);
            amount=scanner.nextDouble();
            if(amount<initialAmount){
                DebitNo++;
                amount=initialAmount;
            }
        }
        System.out.println("Number of debit transactions: "+DebitNo);
    }
}
