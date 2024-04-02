package backend.datarepo.details;

import java.sql.SQLException;
import java.util.List;

public interface InputEmployeeDetails {
    List<Employee> create(List<Employee> employee) throws SQLException;
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections() throws SQLException;
    boolean Validationofdata(List<Employee> employee);

}
