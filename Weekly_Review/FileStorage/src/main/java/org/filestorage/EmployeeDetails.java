package org.filestorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class EmployeeDetails implements OperationEmployeeDetails{
        private static EmployeeAddress employeeAddress;
        static List<Employee> employees=new ArrayList<>();

        private  static  Employee employee;
        private static EmployeeInformation employeeInformation;

        @Override
        public void inputDetails() throws IOException, ClassNotFoundException {
            Scanner scanner= new Scanner(System.in);
            EmployeeDetails employeeDetails=new EmployeeDetails();
            System.out.println("Enter your name :");
            //Scanner scanner=new Scanner(System.in);
            String employeeName = scanner.nextLine();
//            System.out.println("Enter your middle name :");
//            String employeeMiddleName = scanner.nextLine();
//            System.out.println("Enter your Last name :");
//            String employeeLastName = scanner.nextLine();

            System.out.println("Enter your permanent address :");
            System.out.println("Enter the Address :");
            String permanentAddress = scanner.nextLine();
            System.out.println("Enter the House Number :");
            String permanentHouseNumber = scanner.nextLine();
            System.out.println("Enter the city :");
            String permanentCity = scanner.nextLine();
            System.out.println("Enter the State :");
            String permanentState = scanner.nextLine();
            System.out.println("Enter the PinCode :");
            int permanentPinCode = scanner.nextInt();
            System.out.println("Enter your temporary address :");
            System.out.println("Enter the Address :");
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
            employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);

            System.out.println("Enter the Email Id :");
            String emailId = scanner.next();
            System.out.println("Enter the Phone Number :");
            long phoneNumber = scanner.nextLong();
            employeeInformation = new EmployeeInformation(emailId, phoneNumber);
            employee = new Employee(employeeName, employeeAddress, employeeInformation);
            System.out.println("Employee added successfuly");

            List<Employee> employeeInfo=new ArrayList<>();
            employeeInfo.add(employee);
            employeeDetails.create(employeeInfo);

        }

        @Override
        public void displayInput(List<Employee> employees) {
            if (employees.isEmpty()){
                System.out.println("No employess added yet.");
            }
            else{
                System.out.println("employee Details:");
                for(Employee emp: employees){
                    System.out.println("Name= "+emp.getEmployeeName());
                    System.out.println("Permanent Address :" + emp.getAddress().getPermanentAddress()+","+emp.getAddress().getPermanentHouseNumber()+","+emp.getAddress().getPermanentCity()+","+emp.getAddress().getPermanentState()+","+emp.getAddress().getPermanentPinCode());

                    System.out.println("Temporary Address :"+emp.getAddress().getTemporaryAddress()+","+emp.getAddress().getTemporaryHouseNumber()+","+emp.getAddress().getTemporaryCity()+","+emp.getAddress().getTemporaryState()+","+emp.getAddress().getTemporaryPinCode());
                    System.out.println("Email id :" + emp.getAdditionalInformation().getEmailId() + "\nPhone number :" + emp.getAdditionalInformation().getPhoneNumber());
                    System.out.println(" ");

                }
            }
        }


        public static void create(List<Employee> employee) throws IOException, ClassNotFoundException {
            File file=new File("OutputFile.txt");
            if(file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                employees= (List<Employee>) objectInputStream.readObject();//re read file
                employees.addAll(employee);//add new data into existing file
            }else{
                employees=employee;
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
            fileOutputStream.close();

        }
        public void read()  throws IOException, ClassNotFoundException {

            FileInputStream fileInputStream=new FileInputStream("OutputFile.txt");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            List<Employee> loanCard= (List<Employee>) objectInputStream.readObject();
            loanCard.forEach(System.out::println);
//        CreditCard creditCard1=(CreditCard) objectInputStream.readObject();
//        System.out.println(creditCard1);
            objectInputStream.close();
            fileInputStream.close();
        }
    }

