package basics.service;

import java.util.Scanner;

public class MinimumBalance {
    private static Object MinimumBalance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] accBalance = new double[25];
        System.out.println("Enter the Account balance of 20 customers");
        for (int index = 0; index < 20; index++)
            accBalance[index] = scanner.nextDouble();
        cutCharges(accBalance);
        System.out.println("Account balance after deducting charges");
        for (int index = 0; index < 20; index++) {
            System.out.println(accBalance[index]);
        }
    }
    //method to find the charges to be made
    public static void cutCharges(double[] MinimumBalance) {
        double fare = 0;
        for (int index = 0; index < 20; index++) {
            if (MinimumBalance[index] >= 5000 & MinimumBalance[index] <= 9999) {
                MinimumBalance[index] = 0.03 * MinimumBalance[index] - fare;
            } else if (MinimumBalance[index] >= 1000 & MinimumBalance[index] <= 4999) {
                MinimumBalance[index] = 0.05 * MinimumBalance[index] - fare;
            }
        }
    }
}

