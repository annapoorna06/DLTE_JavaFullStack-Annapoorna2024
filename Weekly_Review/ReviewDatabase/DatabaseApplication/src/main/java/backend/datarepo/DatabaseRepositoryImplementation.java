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
    public List<Employee> create(List<Employee> list) throws SQLException {
        List<Employee> createdEmployees = new ArrayList<>();
        for (Employee employee : list) {
            String employeeID = employee.getEmployeebasicDetails().getEmployeeId();
            try {
                // Inserting into Employee table
                String employees = "INSERT INTO Employee(EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeebasicDetails().getEmployeeName());
                preparedStatement.setString(3, employee.getEmployeebasicDetails().getEmailId());
                preparedStatement.setLong(4, employee.getEmployeebasicDetails().getPhoneNumber());
                preparedStatement.executeUpdate();
                // Inserting into Address table
                String insertTemporaryAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,1)";
                preparedStatement = connection.prepareStatement(insertTemporaryAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeeTemporaryAddress().getPinCode());
                preparedStatement.executeUpdate();
                String insertPermanentAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,0)";
                preparedStatement = connection.prepareStatement(insertPermanentAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.executeUpdate();
                createdEmployees.add(employee);
                //  System.out.println(resourceBundleapp.getString("employee.add") + " " + employeeID + " " + resourceBundleapp.getString("employeeAdd.success"));
                logger.info(resourceBundle.getString("employee.added"));
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                //    System.out.println(resourceBundleapp.getString("fail.insert") + " " + employeeID + " " + resourceBundleapp.getString("employee.exists"));
                    logger.warn(resourceBundle.getString("employee.exist"));
                } else {
                    e.printStackTrace();
                }
            }finally {
                closeConnections();
            }
        }
        return createdEmployees;
    }
    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        Employee employee = null;
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE emp.EMPLOYEEID=?";
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
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE ta.PINCODE=? or pa.PINCODE=?";
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
                         resultSet.getString(10),
                         resultSet.getString(11),
                         resultSet.getString(12),
                         resultSet.getString(13),
                         resultSet.getInt(14)
                 );

                 EmployeeAddress temporaryAddr = new EmployeeAddress(
                         resultSet.getString(5),
                         resultSet.getString(6),
                         resultSet.getString(7),
                         resultSet.getString(8),
                         resultSet.getInt(9)
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

            String findAll = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
    public void closeConnections() throws SQLException {
        connection.close();
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

