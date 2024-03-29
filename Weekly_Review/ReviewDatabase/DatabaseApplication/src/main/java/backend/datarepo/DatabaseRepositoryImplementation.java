package backend.datarepo;
import backend.datarepo.details.Employee;
import backend.datarepo.details.EmployeeAddress;
import backend.datarepo.details.EmployeebasicDetails;
import backend.datarepo.details.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
    ConnectionTarget connectionTarget = new ConnectionTarget();
    PreparedStatement preparedStatement;
    Connection connection = connectionTarget.ConnectionApp();
    ResultSet resultSet;
    //ResourceBundle resourceBundleapp = ResourceBundle.getBundle("application");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("dblogger");
    Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);

    public boolean isEstablished() {
        return connection != null;
    }

    @Override
    public void create(List<Employee> list) {
        for (Employee employee : list) {
            String employeeID = employee.getEmployeebasicDetails().getEmployeeId();
            try {
                // Inserting into Employee table
                String employees = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeebasicDetails().getEmployeeName());
                preparedStatement.setString(3, employee.getEmployeebasicDetails().getEmailId());
                preparedStatement.setLong(4, employee.getEmployeebasicDetails().getPhoneNumber());
                preparedStatement.executeUpdate();

                // Inserting into Address table
                String address = "INSERT INTO Address (EmployeeId, permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(address);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.setString(7, employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(8, employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(9, employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(10, employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(11, employee.getEmployeeTemporaryAddress().getPinCode());
                preparedStatement.executeUpdate();

              //  System.out.println(resourceBundleapp.getString("employee.add") + " " + employeeID + " " + resourceBundleapp.getString("employeeAdd.success"));
                logger.info(resourceBundle.getString("employee.added"));

            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                //    System.out.println(resourceBundleapp.getString("fail.insert") + " " + employeeID + " " + resourceBundleapp.getString("employee.exists"));
                    logger.warn(resourceBundle.getString("employee.exist"));
                } else {
                    e.printStackTrace();
                }
            }
        }

    }



    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, " +
                    "a.permanentAddress, a.permanentHouseNumber, a.permanentCity, a.permanentState, a.permanentPinCode, " +
                    "a.temporaryAddress, a.temporaryHouseNumber, a.temporaryCity, a.temporaryState, a.temporaryPinCode " +
                    "FROM Employee e " +
                    "INNER JOIN Address a ON e.EmployeeId = a.EmployeeId " +
                    "WHERE e.EmployeeId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddr, temporaryAddr);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,empAdd.permanentAddress,empAdd.permanentHouseNumber,empAdd.permanentState,empAdd.permanentCity,empAdd.permanentPinCode,empAdd.temporaryAddress,empAdd.temporaryHouseNumber,empAdd.temporaryState,empAdd.temporaryCity,empAdd.temporaryPinCode FROM employee emp " +
            "INNER JOIN Address empAdd ON emp.EmployeeId = empAdd.EmployeeId " +
                    "WHERE empAdd.permanentPinCode = ? OR empAdd.temporaryPinCode = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employees.add(new Employee(basicDetails, permanentAddr, temporaryAddr));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            String findAll = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, " +
                    "a.permanentAddress, a.permanentHouseNumber, a.permanentCity, a.permanentState, a.permanentPinCode, " +
                    "a.temporaryAddress, a.temporaryHouseNumber, a.temporaryCity, a.temporaryState, a.temporaryPinCode " +
                    "FROM Employee e " +
                    "INNER JOIN Address a ON e.EmployeeId = a.EmployeeId";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
                        resultSet.getLong("phoneNumber")
                );
                employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void closeConnections() {

    }

    @Override
    public boolean Validationofdata(List<Employee> employees) {
        for (Employee employee : employees) {
            if (!isValidEmail(employee.getEmployeebasicDetails().getEmailId()) ||
                    !isValidPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber()) ||
                    !isValidPin(employee.getEmployeePermanentAddress().getPinCode()) ||
                    !isValidPin(employee.getEmployeeTemporaryAddress().getPinCode())) {
                return false;
            }
        }
        return true;
    }
    // Validation methods
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "0*(\\d{10})"; // Optional leading zeros followed by 10 digits
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public static boolean isValidPin(int pin) {
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
}

