//package org.console;
//
//import javax.crypto.CipherInputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import static java.lang.System.exit;
//
//public class ConsoleUi {
//    private static ConsoleUi employeeRepository;
////    private static EmployeeDetails employeeDetails;
//
//    public static void main(String[] args) {
//        employeeRepository=new ConsoleUi();
//
//        // List<Employee> employees = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            System.out.println("1.Add Employee\n2.Display Employee\n3.Exit");
//            System.out.println("Enter your choice: ");
//            int choice=scanner.nextInt();
//            scanner.nextLine();
//            switch (choice){
//                case 1:
//                    do{
//                        createEmployee(scanner);
//                    }while(scanner.nextLine().equalsIgnoreCase("yes"));
//                    break;
//                case 2:
//                    E
//                    employeeDetails.read();
//                    break;
//                case 3:exit(0);
//                default:
//                    System.out.println("invalid choice");
//                    break;
//            }
//        }
//    }
//    private static void createEmployee(Scanner scanner){
//        // Scanner scanner= new Scanner(System.in);
//        System.out.println("Enter your name :");
//        //Scanner scanner=new Scanner(System.in);
//        String employeeName = scanner.nextLine();
////            System.out.println("Enter your middle name :");
////            String employeeMiddleName = scanner.nextLine();
////            System.out.println("Enter your Last name :");
////            String employeeLastName = scanner.nextLine();
//
//        System.out.println("Enter your permanent address :");
//        System.out.println("Enter the Address :");
//        String permanentAddress = scanner.nextLine();
//        System.out.println("Enter the House Number :");
//        String permanentHouseNumber = scanner.nextLine();
//        System.out.println("Enter the city :");
//        String permanentCity = scanner.nextLine();
//        System.out.println("Enter the State :");
//        String permanentState = scanner.nextLine();
//        System.out.println("Enter the PinCode :");
//        int permanentPinCode = scanner.nextInt();
//        System.out.println("Enter your temporary address :");
//        System.out.println("Enter the Address :");
//        scanner.nextLine();
//        String temporaryAddress = scanner.nextLine();
//        System.out.println("Enter the House Number :");
//        String temporaryHouseNumber = scanner.nextLine();
//        System.out.println("Enter the city :");
//        String temporaryCity = scanner.nextLine();
//        System.out.println("Enter the State :");
//        String temporaryState = scanner.nextLine();
//        System.out.println("Enter the PinCode :");
//        int temporaryPinCode = scanner.nextInt();
//        // employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);
//
//        System.out.println("Enter the Email Id :");
//        String emailId = scanner.next();
//        System.out.println("Enter the Phone Number :");
//        long phoneNumber = scanner.nextLong();
//        //employeeInformation = new EmployeeInformation(emailId, phoneNumber);
//        //employee = new Employee(employeeName, employeeAddress, employeeInformation);
//        System.out.println("Employee added successfuly");
//        //Employee employee
//        //employeeRepository.createEmployee(employeeName,permanentAddress,permanentHouseNumber,permanentCity,permanentState,permanentPinCode,temporaryAddress,temporaryHouseNumber,temporaryCity,temporaryState,temporaryPinCode,emailId,phoneNumber);
//
//    }
//}
