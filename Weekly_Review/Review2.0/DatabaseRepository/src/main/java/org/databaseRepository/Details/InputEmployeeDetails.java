package org.databaseRepository.Details;

import org.databaseRepository.Details.Employee;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<Employee> employee);
    void displayBasedOnEmployeeId(String employeeID);
    void displayBasedOnPinCode(int pinCode);
    List<Employee> read();
}
