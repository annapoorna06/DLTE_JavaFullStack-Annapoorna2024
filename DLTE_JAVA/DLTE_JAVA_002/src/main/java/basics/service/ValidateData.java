package basics.service;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    public static void main(String[] args) {
        String Name="", PanNo="", Address="";
        String IncomeType="";
        Long mobileNumber=0L, aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        //Read data from user/customer
        System.out.println("Fill your name ");
        Name=scanner.nextLine();
        String nameexp="^[a-zA-Z .']+$";
        Pattern patternname=Pattern.compile(nameexp);
        Matcher matchname=patternname.matcher(Name);
        if(matchname.matches())
            System.out.println("Valid name");
        else
            System.out.println("Invalid name");
        System.out.println("Let us know Income type(Salaried/self employed)");
        IncomeType= scanner.nextLine();
        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("Enter the PAN ");
        PanNo= scanner.next();

        System.out.println("Mention the mobile number ");
        mobileNumber=scanner.nextLong();
    }
}
