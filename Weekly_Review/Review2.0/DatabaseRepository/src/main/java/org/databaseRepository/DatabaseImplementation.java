package org.databaseRepository;
import oracle.jdbc.driver.OracleDriver;
import org.databaseRepository.Details.Employee;
import org.databaseRepository.Details.InputEmployeeDetails;


import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseImplementation implements InputEmployeeDetails {

    Connection connection;
    ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;
    private Object Employee;

    public DatabaseImplementation() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isEstablished(){
        return connection!=null;
    }

    @Override
    public void create(List<Employee> list) {

        for(Employee employee:list){
            String employeeID=employee.getEmployeeId();
            try{
                String employees = "INSERT INTO Employee (id, name) VALUES (?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeName());
                preparedStatement.setString(3,employee.getEmailId());
                preparedStatement.setString(4,employee.getEmailId());
                int resultBasic=preparedStatement.executeUpdate();

                String permanentaddress = "INSERT INTO EmployeePermanentAddress (employeeId,permanentAddress, permanentHouseNumber,permanentCity, permanentState,permanentPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getPermanentAddress().getPermanentAddress());
                preparedStatement.setString(3,employee.getPermanentAddress().getPermanentHouseNumber());
                preparedStatement.setString(4,employee.getPermanentAddress().getPermanentCity());
                preparedStatement.setString(5,employee.getPermanentAddress().getPermanentState());
                preparedStatement.setLong(6,employee.getPermanentAddress().getPermanentPinCode());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryAddress, temporaryHouseNumber,temporaryCity, temporaryState,temporaryPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getTemporaryAddress().getTemporaryAddress());
                preparedStatement.setString(3,employee.getTemporaryAddress().getTemporaryHouseNumber());
                preparedStatement.setString(4,employee.getTemporaryAddress().getTemporaryCity());
                preparedStatement.setString(5,employee.getTemporaryAddress().getTemporaryState());
                preparedStatement.setLong(6,employee.getTemporaryAddress().getTemporaryPinCode());
                int resultTemporary=preparedStatement.executeUpdate();

                if(resultBasic!=0){
                    System.out.println("Basic details inserted");
                }else{
                    System.out.println("failed");
                }
                if(resultTemporary!=0) System.out.println("Temporary address inserted");
                if(resultPermanent!=0) System.out.println("Permanent address inserted");


            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void displayBasedOnEmployeeId(String s) {
        //return null;
    }

    @Override
    public void displayBasedOnPinCode(int i) {
        //return null;
    }

    @Override
    public List<Employee> read() {
        return null;
    }
}
