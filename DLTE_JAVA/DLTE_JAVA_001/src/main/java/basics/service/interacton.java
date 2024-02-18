package basics.service;

import java.util.Scanner;

/*
Personal details: name, aadhaar, pan etc
Income: self employed, salaries,
*/
public class interacton {
    public static void main(String[] args){
        String borrowerName, borrowerPan, borrowerAddress, borrowerEmail;
        Long mobileNumber=0L, aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("fill your name:");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your aadhaar:");

        aadhaar=scanner.nextLong();
        System.out.println("Fill your Pan");
        borrowerPan=scanner.next();
        System.out.println("Let us know your Income type:");

        System.out.println("Mention your mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Dear "+ borrowerName +" Thanks for showing interest in taking loan "+mobileNumber);
    }
}
