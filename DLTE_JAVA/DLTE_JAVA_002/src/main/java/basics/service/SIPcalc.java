package basics.service;

import java.util.Scanner;

public class SIPcalc {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double principalAmount, estimateReturn=0, total=0;
        System.out.println("Enter monthly investment amount: ");
        double monthly=scanner.nextDouble();
        System.out.println("Enter expected annual return: ");
        double annualret=scanner.nextDouble();
        double Return=annualret/100;
        System.out.println("Enter investment period in a year: ");
        int years=scanner.nextInt();
        principalAmount=monthly*12*years;
        double monthlyRate=annualret/12/100;
        double months=12*years;
        total=monthly*((Math.pow((1+monthlyRate),(months))-1)*(1+monthlyRate))/monthlyRate;
        double totalmoneyInvested=months*monthly;
        estimateReturn=total-totalmoneyInvested;
        System.out.println("Principal Amount: "+principalAmount);
        System.out.println("Estimate Return: "+estimateReturn);
        System.out.println("Total: "+total);
    }
}
