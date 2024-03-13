package org.databaseRepository.Details;

public class Employee {
    private String employeeName;
    private String employeeId;
    private String emailId;
    private long phoneNumber;
    private EmployeePermanentAddress permanentAddress;
    private EmployeeTemporaryAddress temporaryAddress;

    public Employee(String employeeName, String employeeId, String emailId, long phoneNumber, EmployeePermanentAddress permanentAddress, EmployeeTemporaryAddress temporaryAddress) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeePermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeePermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeTemporaryAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeTemporaryAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }
}


