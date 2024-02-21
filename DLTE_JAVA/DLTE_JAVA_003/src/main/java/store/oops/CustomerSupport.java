package store.oops;
import java.util.Date;
import java.util.Scanner;

public class CustomerSupport {
    public static void main(String[] args)  {

        CreditCard[] myBank = {
                new CreditCard(23456789L, "annapoorna", new Date(2024, 6, 1), 567, 56789, new Date(2025, 1, 6), new Date(2025, 1, 8), 9482),
                new CreditCard(23456679L, "arundhathi", new Date(2024, 12,7), 518, 55123, new Date(2025, 1, 6), new Date(2025, 1, 8), 9125),
                new CreditCard(23473889L, "akshira", new Date(2030, 1,7), 590, 500, new Date(2025, 1, 6), new Date(2025, 1, 8), 9965),
                new CreditCard(36356789L, "akshatha", new Date(2030, 5,8), 560, 56789, new Date(2024, 1, 5), new Date(2025, 1, 8), 7462),
       };
        CustomerSupport support=new CustomerSupport();
        support.Limit(myBank);
        support.findBillPayDate(myBank);
        support.updatePin(myBank);
        support.updateLimit(myBank);
        }

        public void Limit(CreditCard[] customers){
            double limitAmount;
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter the Amount");
            limitAmount=scanner.nextDouble();
            for(CreditCard each:customers){
                if(each.getCreditCardLimit()>=limitAmount){
                    System.out.println(each.getCreditCardHolder()+" your card has exceeded the limit!");
                }
            }
            scanner.close();
        }

    public void findBillPayDate(CreditCard[] customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date in format (YYYY/MM/DD) ");
        String date = scanner.nextLine();
        String SplitDate[] = date.split("/");
        for (CreditCard each : customers) {
            if (Integer.parseInt(SplitDate[0]) == (each.getDateOfBillPayment().getDate()) && Integer.parseInt(SplitDate[1]) == (each.getDateOfBillPayment().getMonth()) && Integer.parseInt(SplitDate[2]) == (each.getDateOfBillPayment().getYear())) {
                System.out.println(each.getCreditCardHolder()+" you need to pay your bill on "+ each.getDateOfBillPayment().getDate());
            }
        }
        scanner.close();
    }
    public void updatePin(CreditCard[] customers){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your credit Card Number:");
        long creditCardNumber=scanner.nextLong();
        System.out.println("To update your pin enter your old pin");
        int oldPin=scanner.nextInt();
        int pin=0;
        for(CreditCard each:customers){
            if(each.getCreditCardNumber()==creditCardNumber && each.getCreditCardPin()==oldPin){
                System.out.println("enter your new pin");
                each.setCreditCardPin(pin=scanner.nextInt());
                System.out.println("Pin updated!");
            }
        }
        scanner.close();
    }
    public void updateLimit(CreditCard[] customers){
        for(CreditCard each:customers){
            if(each.getDateOfBillGeneration().getDate()==5){
                double currentLimit=each.getCreditCardLimit();
                double newLimit=currentLimit +(currentLimit* 0.05);
                each.setCreditCardLimit((int) newLimit);
                System.out.println("Dear "+each.getCreditCardHolder()+" your limit is updated to "+newLimit);
            }
        }
    }
}


