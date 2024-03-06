package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static org.example.MyBank.loanList;

public class App
{
    public static void main( String[] args )
    {
        //hardcoding list values
        Loan loan1 = new Loan(1, 5000.0, new Date("6/7/2024"), "Open", "Annapoorna", 6363276256L);
        Loan loan2 = new Loan(2, 8000.0, new Date("6/9/2024"), "Closed", "Arundhathi", 9876543210L);
        Loan loan3 = new Loan(3, 10000.0, new Date("6/4/2024"), "Open", "Sinchana", 7890654321L);

        // adding loans to the loanList
        loanList.add(loan1);
        loanList.add(loan2);
        loanList.add(loan3);
        Date StartDate = new Date("6/4/2024");
        Date EndDate = new Date("6/9/2024");
        //using lambda
        MyBank myBank = (startDate, endDate) ->
            loanList.stream()
                    .filter(loan -> loan.getLoanDate().after(startDate) && loan.getLoanDate().before(endDate))
                    .forEach(loan -> System.out.println("Loan Number: " + loan.getLoanNumber() +
                            ", Borrower: " + loan.getBorrowerName() +
                            ", Loan Date: " + loan.getLoanDate()));


        myBank.filterByRange(StartDate, EndDate);
    }
}
