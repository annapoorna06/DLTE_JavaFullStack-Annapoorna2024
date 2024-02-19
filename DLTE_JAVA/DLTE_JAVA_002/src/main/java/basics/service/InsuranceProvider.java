package basics.service;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
        int flag=0;
        String LIC[] = {"life insurance", "health insurance", "no claim insurance"};
        String MaxLifeInsuranceCompany[] = {"day care procedures", "retirement plans", "accidental coverages"};
        String HDFCLifeInsuranceCompany[] = {"operational charges", "maternity benefits", "complimentary checkups"};
        Scanner scanner = new Scanner(System.in);
        String f1 = "";
        System.out.println("Enter the feature you want");
        f1 = scanner.nextLine();
        String offer = "";
        for (int i = 0; i < LIC.length; i++) {
            if (f1.contains(LIC[i]))
                offer+="LIC";
        }
        for (int i = 0; i < MaxLifeInsuranceCompany.length; i++) {
            if (f1.contains(MaxLifeInsuranceCompany[i]))
                offer+="MaxLifeInsuranceCompany";
        }
        for (int i = 0; i < HDFCLifeInsuranceCompany.length; i++) {
            if (f1.contains(HDFCLifeInsuranceCompany[i]))
                offer+="HDFCLifeInsuranceCompany";
        }
        if (offer!=null)
            System.out.println("Company that offers the features are "+offer);
        else
            System.out.println("result not found");
    }
}