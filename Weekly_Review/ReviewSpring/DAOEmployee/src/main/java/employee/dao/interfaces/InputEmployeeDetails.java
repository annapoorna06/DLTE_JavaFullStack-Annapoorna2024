package employee.dao.interfaces;

import employee.dao.entity.Employee;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface InputEmployeeDetails {
    List<Employee> create(List<Employee> employee) throws SQLException;
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections() throws SQLException;
}
