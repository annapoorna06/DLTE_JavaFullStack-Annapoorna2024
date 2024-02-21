package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bonds {
    private Integer maturity;
    private double interestRate;
    private String taxStatus;
    private Integer period;

    public static void main(String[] args) {
        Bonds Government_bond = new Bonds(4, 7.2, "no tax", 5);
        Bonds High_yield_Bonds = new Bonds(5, 6.2, "no tax", 5);
        Bonds Mortgage_Backed_Securities = new Bonds(5, 5.7, "no tax", 5);
        Bonds Zero_Coupon_Bonds = new Bonds(5, 7.1, "no tax", 10);
        Bonds Callable_Bonds = new Bonds(5, 5.1, "no tax", 8);
        Bonds bonds[] = {Government_bond, High_yield_Bonds, Mortgage_Backed_Securities, Zero_Coupon_Bonds, Callable_Bonds};
    }

    public static int maxReturn(Bonds[] bonds) {
        double maxInterest = 0;
        int position = 0, count = 0;
        for (Bonds each : bonds) {
            count++;
            if (each.getInterestRate() > maxInterest) {
                maxInterest = each.getInterestRate();
                position = count;
            }
        }
        System.out.println("The maximum interest rate is "+maxInterest);
        return position;
    }
}

