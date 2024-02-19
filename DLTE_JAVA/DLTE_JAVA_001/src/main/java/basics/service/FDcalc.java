package basics.service;

import java.util.Scanner;

public class FDcalc {
    public static void main(String[] args) {
        Long totalInvestment=0L;
        float roi=0F, EstimatedRet, TotVal;
        int year;
        System.out.println("Enter your initial investment");
        Scanner scanner=new Scanner(System.in);
        totalInvestment=scanner.nextLong();
        System.out.println("Enter the rate of interest");
        roi=scanner.nextFloat();
        System.out.println("Enter time period");
        year=scanner.nextInt();
        EstimatedRet=totalInvestment+(totalInvestment*roi*year/100);
        TotVal=EstimatedRet+totalInvestment;
        System.out.println("Estimated return="+EstimatedRet+"RS");
        System.out.println("Total return= "+TotVal+" RS");
        scanner.close();
    }
}
