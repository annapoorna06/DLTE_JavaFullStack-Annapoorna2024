package org.consoleApp;

import org.example.Employee;
import org.example.EmployeeAddress;
import org.example.EmployeeInformation;
import org.example.InputEmployeeDetails;
import org.fileRepository.FileRepositoryImplementation;
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
        InputEmployeeDetails employeeDetails=new FileRepositoryImplementation();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Logger logger= LoggerFactory.getLogger(ConsoleApp.class);
        EmployeeAddress employeeAddress;
        Employee employee;
        EmployeeInformation employeeInformation;

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
                        employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);
                        System.out.println(resourceBundle.getString("enter.emailId"));
                        String emailId = scanner.next();
                        System.out.println(resourceBundle.getString("enter.phone"));
                        long phoneNumber = scanner.nextLong();
                        employeeInformation = new EmployeeInformation(emailId, phoneNumber);
                        employee = new Employee(employeeName,employeeId, employeeAddress, employeeInformation);
                        System.out.println(resourceBundle.getString("employeeAdd.success"));
                        logger.info(resourceBundle.getString("employeeAdd.success"));
                        List<Employee> employeeInfo=new ArrayList<>();
                        employeeInfo.add(employee);
                        employeeDetails.create(employeeInfo);
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
