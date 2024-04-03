//package frontend.console;
//import backend.datarepo.DatabaseRepositoryImplementation;
//import backend.datarepo.details.Employee;
//import backend.datarepo.details.EmployeeAddress;
//import backend.datarepo.details.EmployeebasicDetails;
//import exception.EmployeeException;
//import frontend.console.pojo.EmployeebasicDetailsConsole;
//import frontend.console.pojo.EmployeeAddressConsole;
//import frontend.console.pojo.EmployeeConsole;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
//import static java.lang.System.exit;
//
//public class ConsoleAppNew {
//    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            DatabaseRepositoryImplementation inputEmployeeDetails = new DatabaseRepositoryImplementation();
//            Logger logger = LoggerFactory.getLogger(ConsoleAppNew.class);
//            ValidationofData validation = new ValidationofData();
//
//            try {
//                while (true) {
//                    printMenu();
//                    int choice = getChoice(scanner);
//
//                    switch (choice) {
//                        case 1:
//                            readEmployeeDetails(scanner, inputEmployeeDetails, validation);
//                            break;
//                        case 2:
//                            displayEmployeeById(scanner, inputEmployeeDetails, logger);
//                            break;
//                        case 3:
//                            displayAllEmployees(inputEmployeeDetails, logger);
//                            break;
//                        case 4:
//                            displayEmployeesByPincode(scanner, inputEmployeeDetails, logger);
//                            break;
//                        case 5:
//                            exit(0);
//                    }
//                }
//            } finally {
//                // Close connections
//                inputEmployeeDetails.closeConnections();
//            }
//        }
//    }
//    private static void printMenu() {
//        System.out.println(resourceBundle.getString("menu.display"));
//        System.out.println(resourceBundle.getString("enter.choice"));
//    }
//
//    private static int getChoice(Scanner scanner) {
//        boolean validate = false;
//        int choice = 0;
//        do {
//            try {
//                choice = scanner.nextInt();
//                validate = true;
//            } catch (InputMismatchException inputMismatchException) {
//                System.out.println(resourceBundle.getString("Enter.number1"));
//                scanner.nextLine();
//            }
//        } while (!validate);
//        return choice;
//    }
//
//    private static void readEmployeeDetails(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, ValidationofData validation) {
//        List<Employee> employees = new ArrayList<>();
//        boolean continueAddingEmployees = true;
//        do {
//            EmployeeConsole employeeConsole = getEmployeeDetailsFromConsole(scanner, validation);
//            if (employeeConsole != null) {
//                Employee employee = translateEmployee(employeeConsole);
//                employees.add(employee);
//                inputEmployeeDetails.create(Collections.singletonList(employee));
//                System.out.print(resourceBundle.getString("add.more"));
//                continueAddingEmployees = scanner.next().equalsIgnoreCase(resourceBundle.getString("yes"));
//            }
//        } while (continueAddingEmployees);
//    }
package frontend.console;

import backend.datarepo.DatabaseRepositoryImplementation;
import backend.datarepo.details.Employee;
import backend.datarepo.details.EmployeeAddress;
import backend.datarepo.details.EmployeebasicDetails;
import exception.EmployeeException;
import frontend.console.pojo.EmployeeAddressConsole;
import frontend.console.pojo.EmployeeConsole;
import frontend.console.pojo.EmployeebasicDetailsConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

import static java.lang.System.exit;

public class ConsoleAppNew {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            DatabaseRepositoryImplementation inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger = LoggerFactory.getLogger(ConsoleAppNew.class);
            ValidationofData validation = new ValidationofData();

            try {
                while (true) {
                    printMenu();
                    int choice = getChoice(scanner);

                    switch (choice) {
                        case 1:
                            readEmployeeDetails(scanner, inputEmployeeDetails, validation);
                            break;
                        case 2:
                            displayEmployeeById(scanner, inputEmployeeDetails, logger);
                            break;
                        case 3:
                            displayAllEmployees(inputEmployeeDetails, logger);
                            break;
                        case 4:
                            displayEmployeesByPincode(scanner, inputEmployeeDetails, logger);
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

    private static void printMenu() {
        System.out.println(resourceBundle.getString("menu.display"));
        System.out.println(resourceBundle.getString("enter.choice"));
    }

    private static int getChoice(Scanner scanner) {
        boolean validate = false;
        int choice = 0;
        do {
            try {
                choice = scanner.nextInt();
                validate = true;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(resourceBundle.getString("Enter.number1"));
                scanner.nextLine();
            }
        } while (!validate);
        return choice;
    }

    private static void readEmployeeDetails(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, ValidationofData validation) {
        List<Employee> employees = new ArrayList<>();
        boolean continueAddingEmployees = true;
        do {
            EmployeeConsole employeeConsole = getEmployeeDetailsFromConsole(scanner, validation);
            if (employeeConsole != null) {
                Employee employee = translateEmployee(employeeConsole);
                employees.add(employee);
                inputEmployeeDetails.create(Collections.singletonList(employee));
                System.out.print(resourceBundle.getString("add.more"));
                continueAddingEmployees = scanner.next().equalsIgnoreCase(resourceBundle.getString("yes"));
            }
        } while (continueAddingEmployees);
    }
    private static EmployeeConsole getEmployeeDetailsFromConsole(Scanner scanner, ValidationofData validation) {
        System.out.println(resourceBundle.getString("enter.employeeDetails"));

        System.out.print(resourceBundle.getString("Enter.name"));
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.id"));
        String id = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.emailId"));
        String email = scanner.nextLine();

        if (!validation.isValidEmail(email)) {
            System.out.println(resourceBundle.getString("invalid.email"));
            return null;
        }

        System.out.print(resourceBundle.getString("enter.phone"));
        long phoneNumber = 0;
        boolean isValidPhoneNumber = false;
        do {
            try {
                phoneNumber = Long.parseLong(scanner.nextLine());
                isValidPhoneNumber = validation.isValidPhoneNumber(phoneNumber);
                if (!isValidPhoneNumber) {
                    System.out.println(resourceBundle.getString("invalid.Phone"));
                    System.out.print(resourceBundle.getString("enter.phone"));
                }
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("invalid.Phone"));
                System.out.print(resourceBundle.getString("enter.phone"));
            }
        } while (!isValidPhoneNumber);

        EmployeebasicDetailsConsole employeeBasicDetailsConsole = new EmployeebasicDetailsConsole(name, id, email, phoneNumber);
        EmployeeAddressConsole employeePermanentAddressConsole = getEmployeeAddressFromConsole(resourceBundle.getString("enter.permanentAddress"));
        EmployeeAddressConsole employeeTemporaryAddressConsole = getEmployeeAddressFromConsole(resourceBundle.getString("enter.temporaryAddress"));

        return new EmployeeConsole(employeeBasicDetailsConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);
    }

    private static EmployeeAddressConsole getEmployeeAddressFromConsole(String addressType) {
        System.out.println(addressType);
        System.out.println(resourceBundle.getString("enter.address"));
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.HouseNumber"));
        String houseNumber = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.city"));
        String city = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.state"));
        String state = scanner.nextLine();

        int pinCode = 0;
        boolean isValidPin = false;
        do {
            System.out.print(resourceBundle.getString("enter.pincode"));
            try {
                pinCode = Integer.parseInt(scanner.nextLine());
                isValidPin = ValidationofData.isValidPin(pinCode);
                if (!isValidPin) {
                    System.out.println(resourceBundle.getString("invalid.Pin"));
                }
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("invalid.Pin"));
            }
        } while (!isValidPin);

        return new EmployeeAddressConsole(address, houseNumber, city, state, pinCode);
    }

    private static void displayEmployeeById(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();
        try {
            Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
            EmployeeConsole employeeConsole = translate(employee);
            System.out.println(employeeConsole.displayEmployeeDetails());
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static void displayAllEmployees(DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {
        List<Employee> allEmployees = inputEmployeeDetails.read();
        if (!allEmployees.isEmpty()) {
            for (Employee emp : allEmployees) {
                EmployeeConsole employeeConsole = translate(emp);
                System.out.println(employeeConsole.displayEmployeeDetails() + "\n");
            }
        } else {
            System.out.println(resourceBundle.getString("employee.not.found"));
            logger.warn(resourceBundle.getString("employee.not.found"));
        }
    }

    private static void displayEmployeesByPincode(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {
        System.out.println(resourceBundle.getString("enter.pincode"));
        EmployeeConsole employeeConsolePin;
        int pinCode = scanner.nextInt();
        try {
            List<backend.datarepo.details.Employee> employee =  inputEmployeeDetails.displayBasedOnPinCode(pinCode);
            if (!employee.isEmpty()) {
                for (backend.datarepo.details.Employee emp : employee) {
                    employeeConsolePin = translate(emp);
                    System.out.println(employeeConsolePin.displayEmployeeDetails());
                    System.out.println();
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }
    private static EmployeeConsole translate(backend.datarepo.details.Employee employee) {
        EmployeebasicDetailsConsole employeeBasicDetailsConsole = new EmployeebasicDetailsConsole();
        EmployeeAddressConsole tempAddress = new EmployeeAddressConsole();
        EmployeeAddressConsole permAddress = new EmployeeAddressConsole();

        employeeBasicDetailsConsole.setName(employee.getEmployeebasicDetails().getEmployeeName());
        employeeBasicDetailsConsole.setId(employee.getEmployeebasicDetails().getEmployeeId());
        employeeBasicDetailsConsole.setEmail(employee.getEmployeebasicDetails().getEmailId());
        employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber());

        permAddress.setAddress(employee.getEmployeePermanentAddress().getAddress());
        permAddress.setHouseNumber(employee.getEmployeePermanentAddress().getHouseNumber());
        permAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permAddress.setState(employee.getEmployeePermanentAddress().getState());
        permAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        tempAddress.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        tempAddress.setHouseNumber(employee.getEmployeeTemporaryAddress().getHouseNumber());
        tempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        tempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        tempAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
        return new EmployeeConsole(employeeBasicDetailsConsole, permAddress, tempAddress);
    }
    private static Employee translateEmployee(EmployeeConsole employeeConsole) {
        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
        EmployeebasicDetails employeebasicDetails = new EmployeebasicDetails();
        employeebasicDetails.setEmployeeName(employeeConsole.getBasicDetails().getName());
        employeebasicDetails.setEmployeeId(employeeConsole.getBasicDetails().getId());
        employeebasicDetails.setEmailId(employeeConsole.getBasicDetails().getEmail());
        employeebasicDetails.setPhoneNumber(employeeConsole.getBasicDetails().getPhoneNumber());

        employeePermanentAddress.setAddress(employeeConsole.getPermanentAddress().getAddress());
        employeePermanentAddress.setHouseNumber(employeeConsole.getPermanentAddress().getHouseNumber());
        employeePermanentAddress.setCity(employeeConsole.getPermanentAddress().getCity());
        employeePermanentAddress.setState(employeeConsole.getPermanentAddress().getState());
        employeePermanentAddress.setPinCode(employeeConsole.getPermanentAddress().getPinCode());

        employeeTemporaryAddress.setAddress(employeeConsole.getTemporaryAddress().getAddress());
        employeeTemporaryAddress.setHouseNumber(employeeConsole.getTemporaryAddress().getHouseNumber());
        employeeTemporaryAddress.setCity(employeeConsole.getTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employeeConsole.getTemporaryAddress().getState());
        employeeTemporaryAddress.setPinCode(employeeConsole.getTemporaryAddress().getPinCode());

        return new Employee(employeebasicDetails, employeePermanentAddress, employeeTemporaryAddress);

    }
}




