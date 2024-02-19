package basics.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args) {
                //Details of the user/customer
                String Name="", PanNo="", Address="";
                String IncomeType="";
                Long mobileNumber=0L, aadhaar=0L;
                Scanner scanner=new Scanner(System.in);
                //Read data from user/customer
                System.out.println("Fill your name ");
                Name=scanner.nextLine();
                System.out.println("Let us know Income type(Salaried/self employed)");
                IncomeType= scanner.nextLine();
                System.out.println("Fill your aadhaar number");
                aadhaar=scanner.nextLong();
                System.out.println("Enter the PAN ");
                PanNo= scanner.next();

                System.out.println("Mention the mobile number ");
                mobileNumber=scanner.nextLong();
                //print the details of user/customer
                System.out.println("Dear "+Name+" \nThanks for showing interest on taking loan in XYZ Bank, your application has been " +
                        "submitted and further details will be sent to you through SMS to "+mobileNumber);
            }
        }
