package org.console;
import org.databaseRepository.DatabaseImplementation;

import org.databaseRepository.Details.Employee;
import org.databaseRepository.Details.EmployeePermanentAddress;
import org.databaseRepository.Details.InputEmployeeDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConsoleApp {
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        InputEmployeeDetails employeeDetails=new DatabaseImplementation();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger= LoggerFactory.getLogger(ConsoleApp.class);
        Employee employee;
        EmployeePermanentAddress employeePermanentAddress;
        Validation validation=new Validation();
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            System.out.println(resourceBundle.getString("enter.choice"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        System.out.println(resourceBundle.getString("Enter.name"));
                        //Scanner scanner=new Scanner(System.in);
                        String employeeName = scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.id"));
                        String employeeId=scanner.next();
                        scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.permanentAddress"));
                        System.out.println(resourceBundle.getString("enter.address"));
                        String permanentAddress = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.HouseNumber"));
                        String permanentHouseNumber = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.city"));
                        String permanentCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.state"));
                        String permanentState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.pincode"));
                        int permanentPinCode = scanner.nextInt();
                        if (!validation.isValidPin(permanentPinCode)) {
                            System.out.println("Invalid PIN. PIN should be 6 digits long.");
                            continue;
                        }
                        System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                        System.out.println(resourceBundle.getString("enter.address"));
                        scanner.nextLine();
                        String temporaryAddress = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.HouseNumber"));
                        String temporaryHouseNumber = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.city"));
                        String temporaryCity = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.state"));
                        String temporaryState = scanner.nextLine();
                        System.out.println(resourceBundle.getString("enter.pincode"));
                        int temporaryPinCode = scanner.nextInt();
                        if (!validation.isValidPin(temporaryPinCode)) {
                            System.out.println("Invalid PIN. PIN should be 6 digits long.");
                            continue;
                        }
//                        employeePermanentAddress = new EmployeePermanentAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode);
                        System.out.println(resourceBundle.getString("enter.emailId"));
                        String emailId = scanner.next();
                        if (!validation.isValidEmail(emailId)) {
                            System.out.println("Invalid email address format. Please try again.");
                            continue;
                        }
                        System.out.println(resourceBundle.getString("enter.phone"));
                        long phoneNumber = scanner.nextLong();
                        scanner.nextLine();
                        if (!validation.isValidPhoneNumber(phoneNumber)) {
                            System.out.println("Invalid phone number format. Please try again.");
                            continue;
                        }
                        employeeInformation = new EmployeeInformation(emailId,phoneNumber);
                        employee = new Employee(employeeName,employeeId, employeeAddress, employeeInformation);
                        List<Employee> employeeInfo=new ArrayList<>();
                        employeeInfo.add(employee);
                        employeeDetails.create(employeeInfo);
                        System.out.println(resourceBundle.getString("employeeAdd.success"));
                        logger.info(resourceBundle.getString("employeeAdd.success"));
                        System.out.println(resourceBundle.getString("add.more"));
                    }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                    break;
                case 2:  System.out.println(resourceBundle.getString("enter.id"));

                    System.out.println(employeeDetails.displayBasedOnEmployeeId(scanner.next()));
                    break;
                case 3:
                    System.out.println(employeeDetails.read());
                    break;
                case 4:
                    System.out.println(resourceBundle.getString("enter.pincode"));
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
            }
        }

    }
}