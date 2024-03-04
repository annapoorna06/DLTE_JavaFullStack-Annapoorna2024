package org.example;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args)  {
        MyBank myBank= new Implementation();
        ArrayList<Loans> loans = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add new loan");
            System.out.println("2. Check available loans");
            System.out.println("3. Check closed loans");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter loan number: ");
                    int loanNumber = scanner.nextInt();
                    System.out.print("Enter loan amount: ");
                    double loanAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter loan Status");
                    String loanStatus=scanner.next();
                    System.out.print("Enter borrower name: ");
                    scanner.nextLine();
                    String borrowerName = scanner.nextLine();
                    System.out.print("Enter borrower contact: ");
                    String borrowerContact = scanner.nextLine();

                    Loans newLoan = new Loans(loanNumber, loanAmount, loanStatus, borrowerName, borrowerContact);
                    myBank.addNewLoan(loans, newLoan);
                    System.out.println("New loan added successfully.");
                    break;
                case 2:
                    ArrayList<Loans> availableLoans = myBank.checkAvailableLoans(loans);
                    System.out.println("Available Loans: " + availableLoans);
                    break;
                case 3:
                    ArrayList<Loans> closedLoans = myBank.checkClosedLoans(loans);
                    System.out.println("Closed Loans: " + closedLoans);
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}



