package basics.service;
import java.util.Scanner;
public class IncomeTax {
    public static void main(String[] args) {
        String ch;
        double salary, taxAmount, taxPercent=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Income salary: ");
        salary = sc.nextDouble();
        System.out.println("Enter your choice of regime");
        ch = sc.next();
        switch (ch) {
            case "oldregime":
            case "OldRegime":
            case "OLDREGIME":
                if (salary >= 0 && salary <= 250000) {
                    System.out.println("You need not pay tax:Exempted");
                } else if (salary > 250000 && salary <= 500000) {
                    taxPercent = 5;
                } else if (salary > 500000 && salary <= 1000000) {
                    taxPercent = 20;
                } else {
                    taxPercent = 30;
                }
                break;
            case "newregime":
            case "NewRegime":
            case "NEWREGIME":
                if (salary >= 0 && salary <= 250000) {
                    System.out.println("You need not pay tax:Exempted");
                } else if (salary > 250000 && salary <= 500000) {
                    taxPercent = 5;
                } else if (salary > 500000 && salary <= 750000) {
                    taxPercent = 10;
                } else if (salary > 750000 && salary <= 1000000) {
                    taxPercent = 15;
                } else if (salary > 1000000 && salary <= 1250000) {
                    taxPercent = 20;
                } else {
                    taxPercent = 25;
                }
        }
        taxAmount = (salary / 100) * taxPercent;
        System.out.println("Tax to be paid is " + taxAmount + " that is " + taxPercent + "%");
    }
}

