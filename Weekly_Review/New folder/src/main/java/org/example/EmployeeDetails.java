package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDetails implements ReadInfo {
    private static EmployeePermanentAddress employeePermanentAddress;
    //private  static  Employee employee;
    private static EmployeeTemporaryAddress employeeTemporaryAddress;
    private static EmployeeInformation employeeInformation;


    private static void create(List<Employee> employeeList) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("File.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
//        objectOutputStream.writeObject(employeeTemporaryList);
//        objectOutputStream.writeObject(employeePermanentAddress);
        objectOutputStream.close();
        fileOutputStream.close();
    }


//    public static void displayInput(List<Employee> employees) {
//        if (employees.isEmpty()){
//            System.out.println("No employee added yet.");
//        }
//        else{
//            System.out.println("employee Details:");
//            for(Employee emp: employees){
//                System.out.println("Name= "+emp.getEmployeeName());
//                System.out.println("Permanent Address :" + emp.getPermanentAddress().getPermanentAddress()+","+emp.getPermanentAddress().getPermanentHouseNumber()+","+emp.getPermanentAddress().getPermanentCity()+","+emp.getPermanentAddress().getPermanentState()+"-"+emp.getPermanentAddress().getPermanentPinCode());
//
//                System.out.println("Temporary Address :"+emp.getTemporaryAddress().getTemporaryAddress()+","+emp.getTemporaryAddress().getTemporaryHouseNumber()+","+emp.getTemporaryAddress().getTemporaryCity()+","+emp.getTemporaryAddress().getTemporaryState()+"-"+emp.getTemporaryAddress().getTemporaryPinCode());
//                System.out.println("Email id :" + emp.getAdditionalInfo().getEmailId() + "\nPhone number :" + emp.getAdditionalInfo().getPhoneNumber());
//                System.out.println(" ");
//
//            }
//        }
//
//
//    }

    @Override
    public List<Employee> readEmployee() throws IOException {

        System.out.println("Enter your name :");
        Scanner scanner=new Scanner(System.in);
        String employeeName = scanner.nextLine();
        System.out.println("Enter the Email Id :");
        String emailId = scanner.next();
        System.out.println("Enter the Phone Number :");
        long phoneNumber = scanner.nextLong();
        employeeInformation = new EmployeeInformation(emailId, phoneNumber);
        Employee employee = new Employee();
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(employee);
        EmployeeDetails.create(employeeList);
        return employeeList;

    }

    @Override
    public void readTemporaryAddress() {
        System.out.println("Enter your temporary address :");
        System.out.println("Enter the Address :");
        Scanner scanner=new Scanner(System.in);
        scanner.nextLine();
        String temporaryAddress = scanner.nextLine();
        System.out.println("Enter the House Number :");
        String temporaryHouseNumber = scanner.nextLine();
        System.out.println("Enter the city :");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int temporaryPinCode = scanner.nextInt();

        employeeTemporaryAddress=new EmployeeTemporaryAddress(temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);
    }

    @Override
    public EmployeePermanentAddress readPermanentAddress() throws IOException {
        System.out.println("Enter your permanent address :");
        System.out.println("Enter the Address :");
        Scanner scanner=new Scanner(System.in);
        String permanentAddress = scanner.nextLine();
        System.out.println("Enter the House Number :");
        String permanentHouseNumber = scanner.nextLine();
        System.out.println("Enter the city :");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int permanentPinCode = scanner.nextInt();
        List<Employee> employeeList=new ArrayList<>();
        EmployeePermanentAddress employeePermanentAddress = new EmployeePermanentAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode);

        employeeList.add(employeePermanentAddress);
        EmployeeDetails.create(employeeList);
        return employeeList;
    }
}