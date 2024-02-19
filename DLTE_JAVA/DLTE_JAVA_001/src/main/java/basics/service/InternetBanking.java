package basics.service;

import java.util.Scanner;

public class InternetBanking {
    public static void main(String[] args) {
        //account holder's name, mobile number, account number, debit/credit card number
        String name, accNo, cardNo;
        long mobNo;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Account Holder's Name: ");
        name=scanner.nextLine();
        System.out.println("Enter mobile number");
        mobNo=scanner.nextLong();
        System.out.println("Enter valid account number");
        accNo=scanner.next();
        System.out.println("Enter debit/ credit card number");
        cardNo=scanner.next();
        System.out.println("Dear "+name+", your Internet banking facilities has been invoked, any further messages will be sent " +
                "to "+mobNo);
    }
}
