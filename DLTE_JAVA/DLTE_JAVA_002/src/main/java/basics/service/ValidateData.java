package basics.service;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    public static void main(String[] args) {
        String Name="", PanNo="", Address="";
        String emailId,  aadhaar;
        String mobileNumber;
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

        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.next();
        String aadhaarexp="[1-9]{1}[0-9]{11}";
        Pattern patternaadhaar=Pattern.compile(aadhaarexp);
        Matcher matchaadhaar=patternaadhaar.matcher(aadhaar);
        if(matchaadhaar.matches())
            System.out.println("Valid Aadhaar number");
        else
            System.out.println("Invalid Aadhaar");

        System.out.println("Enter the PAN ");
        PanNo= scanner.next();
        String panCardexp="[A-Z]{5}[0-9]{4}[A-Z]";
        Pattern patternpan=Pattern.compile(panCardexp);
        Matcher matchpanCard=patternpan.matcher(PanNo);
        if(matchpanCard.matches())
            System.out.println("Valid Pan number");
        else
            System.out.println("Invalid Pan");

        System.out.println("Mention the mobile number ");
        mobileNumber=scanner.next();
        String mobileNoexp="[6-9]{1}[0-9]{9}";
        Pattern patternMobile=Pattern.compile(mobileNoexp);
        Matcher matchMobile=patternMobile.matcher(mobileNumber);
        if(matchpanCard.matches())
            System.out.println("Valid Pan number");
        else
            System.out.println("Invalid Pan");

        System.out.println("Enter your email id ");
        emailId=scanner.next();
        int _atpos=emailId.indexOf('@');
        int _dotpos=emailId.indexOf('.');
        if((_dotpos-_atpos)>=3)
            System.out.println("Valid Email Id");
        else
            System.out.println("Invalid Email Id");


    }
}
