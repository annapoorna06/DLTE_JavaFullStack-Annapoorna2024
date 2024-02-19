package basics.service;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args) {
        //account holder's name, mobile number, account number, generation of pin
        String name, accNo, pin1, pin2;
        long mobNo;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Account Holder's Name: ");
        name = scanner.nextLine();
        System.out.println("Enter mobile number");
        mobNo = scanner.nextLong();
        System.out.println("Enter valid account number");
        accNo = scanner.next();
        System.out.println("Generate a pin");
        pin1 = scanner.next();
        System.out.println("re-enter the pin");
        pin2 = scanner.next();
        for (int i = 0; i < 3; i++) {
            if (pin1.equals(pin2)) {
                System.out.println("Dear " + name + ", you have been logged in successfully. Thank you for choosing XYZ bank");
            } else {
                System.out.println("ReEnter the pin again");
                pin2 = scanner.next();
            }
        }
        System.out.println("Failed to generate account in online banking system");
    }

}
