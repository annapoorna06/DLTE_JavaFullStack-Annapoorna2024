package org.example;

import java.util.ArrayList;

public interface MyBank {

        ArrayList<Loans> readLoansFromFile(String filePath);
        void writeLoansToFile(ArrayList<Loans> loans, String filePath);
        void addNewLoan(ArrayList<Loans> loans, Loans newLoan);
        ArrayList<Loans> checkAvailableLoans(ArrayList<Loans> loanStatus);
        ArrayList<Loans> checkClosedLoans(ArrayList<Loans> loans);

}



