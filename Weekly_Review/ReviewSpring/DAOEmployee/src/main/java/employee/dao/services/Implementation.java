package employee.dao.services;

import employee.dao.entity.Employee;
import employee.dao.entity.EmployeeAddress;
import employee.dao.entity.EmployeebasicDetails;
import employee.dao.interfaces.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class Implementation implements InputEmployeeDetails {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(Implementation.class);

    @Override
    public List<Employee> create(List<Employee> list) {
        List<Employee> createdEmployees = new ArrayList<>();
        try {
            for (Employee employee : list) {
                String employeeID = employee.getEmployeebasicDetails().getEmployeeId();

                // Inserting into Employee table
                String employees = "INSERT INTO Employee(EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(employees, employeeID, employee.getEmployeebasicDetails().getEmployeeName(),
                        employee.getEmployeebasicDetails().getEmailId(), employee.getEmployeebasicDetails().getPhoneNumber());

                // Inserting into Address table (temporary)
                String insertTemporaryAddress = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,1)";
                jdbcTemplate.update(insertTemporaryAddress, employeeID, employee.getEmployeeTemporaryAddress().getAddress(),
                        employee.getEmployeeTemporaryAddress().getHouseNumber(), employee.getEmployeeTemporaryAddress().getCity(),
                        employee.getEmployeeTemporaryAddress().getState(), employee.getEmployeeTemporaryAddress().getPinCode());

                // Inserting into Address table (permanent)
                String insertPermanentAddress = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,0)";
                jdbcTemplate.update(insertPermanentAddress, employeeID, employee.getEmployeePermanentAddress().getAddress(),
                        employee.getEmployeePermanentAddress().getHouseNumber(), employee.getEmployeePermanentAddress().getCity(),
                        employee.getEmployeePermanentAddress().getState(), employee.getEmployeePermanentAddress().getPinCode());

                createdEmployees.add(employee);
                logger.info("Employee added: " + employeeID);
            }
        } catch (DataAccessException e) {
            logger.error("Error creating employees", e);
        }
        return createdEmployees;
    }

    @Override
    public void closeConnections() throws SQLException {

    }
    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 " +
                "WHERE emp.EmployeeId = ?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{employeeId}, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Error displaying employee with ID: " + employeeId, e);
            return null;
        }
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 " +
                "WHERE ta.PINCODE = ? OR pa.PINCODE = ?";

        try {
            return jdbcTemplate.query(query, new Object[]{pinCode, pinCode}, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Error displaying employees based on pin code: " + pinCode, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Employee> read() {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";

        try {
            return jdbcTemplate.query(query, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Error reading employees", e);
            return new ArrayList<>();
        }
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                    rs.getString("EmployeeName"),
                    rs.getString("EmployeeId"),
                    rs.getString("emailId"),
                    rs.getString("phoneNumber")
            );

            EmployeeAddress permanentAddr = new EmployeeAddress(
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getInt(14)
            );

            EmployeeAddress temporaryAddr = new EmployeeAddress(
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9)
            );

            return new Employee(basicDetails, permanentAddr, temporaryAddr);
        }
    }
}

