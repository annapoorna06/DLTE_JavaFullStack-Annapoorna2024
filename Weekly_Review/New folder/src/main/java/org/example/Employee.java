package org.example;

public class Employee {
    private String employeeName;
    private EmployeeInformation additionalInfo;
    private EmployeePermanentAddress permanentAddress;
    private EmployeeTemporaryAddress temporaryAddress;

    public Employee() {
        this.employeeName = employeeName;
        this.additionalInfo = additionalInfo;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", additionalInfo=" + additionalInfo +
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

    public EmployeeInformation getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(EmployeeInformation additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getPermanentAddress() {
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
