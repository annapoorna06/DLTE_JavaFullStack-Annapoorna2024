package org.example;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeName;
    private String employeeId;
    private  EmployeeAddress address;
    private  EmployeeInformation additionalInformation;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", address=" + address +
                ", additionalInformation=" + additionalInformation +
                '}';
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeAddress getAddress() {
        return address;
    }

    public void setAddress(EmployeeAddress address) {
        this.address = address;
    }

    public EmployeeInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(EmployeeInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Employee(String employeeName, String employeeId, EmployeeAddress address, EmployeeInformation additionalInformation) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.address = address;
        this.additionalInformation = additionalInformation;
    }
}
