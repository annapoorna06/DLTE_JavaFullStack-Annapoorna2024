package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Implementation implements MyBank {

    @Override
    public ArrayList<Loans> readLoansFromFile(String filePath) {
        ArrayList<Loans> loans = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
             loans = (ArrayList<Loans>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void writeLoansToFile(ArrayList<Loans> loans, String filePath) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
                objectOutputStream.writeObject(loans);
                System.out.println("Loans successfully written to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void addNewLoan(ArrayList<Loans> loans, Loans newLoan) {
        //changes made!!
        loans=readLoansFromFile("debits.txt");
        loans.add(newLoan);
        writeLoansToFile( loans,"debits.txt");
    }

    @Override
    public ArrayList<Loans> checkAvailableLoans(ArrayList<Loans> loans) {
        ArrayList<Loans> availableLoans = new ArrayList<>();
        loans = readLoansFromFile("debits.txt");
        for (Loans loan : loans) {
            if ("open".equals(loan.getLoanStatus())) {
                availableLoans.add(loan);
            }
        }
        return availableLoans;
    }

    @Override
    public ArrayList<Loans> checkClosedLoans(ArrayList<Loans> loans) {
        ArrayList<Loans> closedLoans = new ArrayList<>();
        loans = readLoansFromFile("debits.txt");
        for (Loans loan : loans) {
            if ("closed".equals(loan.getLoanStatus())) {
                closedLoans.add(loan);
            }
        }
        return closedLoans;
    }
}


