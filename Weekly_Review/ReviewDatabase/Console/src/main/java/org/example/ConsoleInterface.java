package org.example;
import exception.EmployeeException;
import org.example.details.Employee;
import org.example.details.EmployeeAddress;
import org.example.details.EmployeebasicDetails;
import org.example.details.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import static java.lang.System.exit;
public class ConsoleInterface {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger= LoggerFactory.getLogger(ConsoleInterface.class);
            ValidationofData validation=new ValidationofData();
            try {
                List<Employee> employees = new ArrayList<>();
                while (true) {
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    switch (scanner.nextInt()) {
                        case 1:do {
                            System.out.println(resourceBundle.getString("enter.employeeDetails"));
                            System.out.print(resourceBundle.getString("Enter.name"));
                            scanner.nextLine();
                            String name = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.id"));
                            String id = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.emailId"));
                            String email = scanner.nextLine();

                            if (!validation.isValidEmail(email)) {
                                System.out.println(resourceBundle.getString("invalid.email"));
                                break;
                            }

                            System.out.print(resourceBundle.getString("enter.phone"));
                            long phoneNumber = Long.parseLong(scanner.nextLine());

                            if (!validation.isValidPhoneNumber(phoneNumber)) {
                                System.out.println(resourceBundle.getString("invalid.Phone"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("enter.permanentAddress"));
                            System.out.print(resourceBundle.getString("enter.address"));
                            String permanentAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.HouseNumber"));
                            String permanentHouseNumber = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.city"));
                            String permanentCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.state"));
                            String permanentState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.pincode"));
                            int permanentPinCode = Integer.parseInt(scanner.nextLine());

                            if (!validation.isValidPin(permanentPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            System.out.println(resourceBundle.getString("enter.temporaryaddress"));
                            System.out.print(resourceBundle.getString("enter.address"));
                            String temporaryAddress = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.HouseNumber"));
                            String temporaryHouseNumber = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.city"));
                            String temporaryCity = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.state"));
                            String temporaryState = scanner.nextLine();

                            System.out.print(resourceBundle.getString("enter.pincode"));
                            int temporaryPinCode = Integer.parseInt(scanner.nextLine());

                            if (!validation.isValidPin(temporaryPinCode)) {
                                System.out.println(resourceBundle.getString("invalid.Pin"));
                                break;
                            }

                            EmployeebasicDetails basicDetails = new EmployeebasicDetails(name, id, email, phoneNumber);
                            EmployeeAddress permanentAddr = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentState, permanentCity, permanentPinCode);
                            EmployeeAddress temporaryAddr = new EmployeeAddress(temporaryAddress, temporaryHouseNumber, temporaryState, temporaryCity, temporaryPinCode);

                            Employee employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
                            List<Employee> employeeInfo = new ArrayList<>();
                            employeeInfo.add(employee);
                            inputEmployeeDetails.create(employeeInfo);

                            System.out.print(resourceBundle.getString("add.more"));
                        }while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
                            break;
                        case 2:
                            System.out.println(resourceBundle.getString("enter.id"));
                            String employeeId = scanner.next();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch(EmployeeException e){
                            }
                            break;

                        case 3:
                            List<Employee> allEmployees = inputEmployeeDetails.read();
                            if (!allEmployees.isEmpty()) {
                                for (Employee emp : allEmployees) {
                                    System.out.println(emp.displayEmployeeDetails());
                                    System.out.println();
                                }
                            } else {
                                System.out.println(resourceBundle.getString("employee.not.found"));
                                logger.warn(resourceBundle.getString("employee.not.found"));
                            }
                            break;
                        case 4:
                            System.out.println(resourceBundle.getString("enter.pincode"));
                            int pinCode = scanner.nextInt();
                            try {
                                Employee employee = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
                                System.out.println(employee.displayEmployeeDetails());
                            } catch (EmployeeException e) {
                                System.out.println(e.getMessage());
                                logger.warn(e.getMessage());
                            }
                            break;
                        case 5:
                            exit(0);
                    }
                }
            } finally {
                // Close connections
                inputEmployeeDetails.closeConnections();
            }
        }
    }
}
