package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1.Add Employee\n2.Display Employee");
            System.out.println("Enter your choice: ");
            String choice=scanner.nextLine();
            //scanner.nextLine();
            switch (choice){
                case "1":do{
                    EmployeeDetails employeeDetails = new EmployeeDetails();
                    employeeDetails.inputDetail(scanner);
                    Employee employee=EmployeeDetails.inputDetail(scanner);
                    employees.add(employee);
                    System.out.println("Do you want to add one more employee?(yes/no): ");
                    scanner.nextLine();
                }while(scanner.nextLine().equalsIgnoreCase("yes"));
                    break;
                case "2":EmployeeDetails.displayInput(employees);
                    break;
                default:
                    System.out.println("Wrong input! Give the correct choice...");
                    break;
            }
        }
    }

}