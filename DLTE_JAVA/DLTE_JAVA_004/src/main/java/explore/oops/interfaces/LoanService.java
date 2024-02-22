package explore.oops.interfaces;

import java.util.Scanner;

public class LoanService implements MyBank{
        private Loan[] loans;
        private int loanCount;

        public LoanService() {
            this.loans = new Loan[100];
            this.loanCount = 0;
        }

        public static void main(String[] args) {
            LoanService bank=new LoanService();
            Scanner scanner=new Scanner(System.in);
            int choice;
            do{
                System.out.println("1.Add new Loan");
                System.out.println("2.Check available loans");
                System.out.println("3.check closed loans");
                System.out.println("4.Exit\nEnter your choice: ");
                choice =scanner.nextInt();
                switch(choice){
                    case 1:bank.addNewLoan(getLoanFromUser(scanner));
                           break;
                    case 2:displayLoans("Open Loans",bank.checkAvailableLoans());
                           break;
                    case 3:displayLoans("Closed Loans", bank.checkClosedLoans());
                           break;
                    case 4:
                        System.out.println("Exiting");
                        break;
                }
            }while (choice!=4);
            scanner.close();
        }
        private static Loan getLoanFromUser(Scanner scanner){
            System.out.println("Enter loan number");
            int loanNumber=scanner.nextInt();
            System.out.println("Enter loan date");
            String loanDate=scanner.next();
            System.out.println("Enter loan amount");
            double loanAmount=scanner.nextDouble();
            System.out.println("Enter loan Status");
            String loanStatus=scanner.next();
            System.out.println("Enter borrower name");
            String borrowerName=scanner.next();
            System.out.println("Enter borrower contact");
            String borrowerContact=scanner.next();
            return new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
        }
        @Override
        public void addNewLoan(Loan loan){
            if(loanCount < loans.length){
                loans[loanCount++]=loan;
                System.out.println("New Loan added");
            }
            else{
                System.out.println("Can't add more loans");
            }
        }
        public Loan[] checkAvailableLoans(){
            int openCount=0;
            for(Loan loan: loans){
                if(loan!=null && loan.getLoanStatus().equalsIgnoreCase("Open")){
                    openCount++;
                }
            }
            Loan[] availableLoans=new Loan[openCount];
            int index=0;
            for (Loan each:loans){
                if (each != null && each.getLoanStatus().equalsIgnoreCase("open")) {
                    availableLoans[index++]=each;
                }
            }
            return availableLoans;
        }
        public Loan[] checkClosedLoans(){
            int closedLoansCount=0;
            for (Loan each:loans){
                if (each != null && each.getLoanStatus().equalsIgnoreCase("closed")) {
                    closedLoansCount++;
                }
            }
            Loan[] closedLoans=new Loan[closedLoansCount];
            int index=0;
            for(Loan each:loans){
                if (each!=null && each.getLoanStatus().equalsIgnoreCase("Closed")){
                    closedLoans[index++]=each;
                }
            }
            return closedLoans;
        }
        private static void displayLoans(String title, Loan[] loanArray){
            System.out.println("\n"+title+":");
            if (loanArray.length>0){
                for (Loan each:loanArray){
                    System.out.println(each);
                }

            }
            else
                System.out.println("no loans");
        }
}
