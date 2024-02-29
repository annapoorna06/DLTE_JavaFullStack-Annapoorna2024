package org.example;

public class App {
    public static void main(String[] args) {

        // Create instance to Generic MyBankDatabase to store CreditCard object and perform CRUD
        MyBankDatabase<CreditCard> creditCardDatabase = new MyBankDatabase<>();
        CreditCard card1 = new CreditCard("1234-5678-9012-3456", 1000.0);
        CreditCard card2 = new CreditCard("9876-5432-1098-7654", 2000.0);
        //creating credit card
        creditCardDatabase.create(card1);
        creditCardDatabase.create(card2);
        System.out.println("Card Created: "+card1 +" and\n" +card2+"\n" );
        //reading a card
        System.out.println("Retrieved Credit Card: " + creditCardDatabase.read(0)+"\n");
        //updating some value of the card
        card1.setBalance(1500.0);
        creditCardDatabase.update(0, card1);
        System.out.println("Updated Credit Card " + creditCardDatabase.read(0)+"\n");
        //deleting a card
        creditCardDatabase.delete(1);
        System.out.println("Credit Card deleted!");

        // Create instance to Generic MyBankDatabase to store Transaction object and perform CRUD
        System.out.println("\nTransaction Database:");
        MyBankDatabase<Transaction> transactionDatabase = new MyBankDatabase<>();
        Transaction transaction1 = new Transaction(1, 500.0, "29-02-2024");
        Transaction transaction2 = new Transaction(2, 1000.0, "12-02-2024");
        //making a transaction
        transactionDatabase.create(transaction1);
        transactionDatabase.create(transaction2);
        System.out.println("Transaction has been made ");
        //reading a transaction
        System.out.println("Retrieved Transaction 0: " + transactionDatabase.read(0));
        //deleting a transaction
        transactionDatabase.delete(1);
        System.out.println("Transaction deleted!");

    }
}
