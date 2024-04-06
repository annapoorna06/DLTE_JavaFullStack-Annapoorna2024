package employee.dao.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeebasicDetails {
    @NotNull(message = "name.null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name.invalid")
    private String employeeName;

    @NotNull(message = "id.null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "id.invalid")
    private String employeeId;

    @NotNull(message = "email.null")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email.invalid")
    private String emailId;

    @NotNull(message = "phone.null")
    @Pattern(regexp = "^[0-9]{10}$", message = "phone.invalid")
    private String phoneNumber;

    public EmployeebasicDetails(@NotNull(message = "name.null") @Pattern(regexp = "^[a-zA-Z ]+$", message = "name.invalid") String employeeName, @NotNull(message = "id.null") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "id.invalid") String employeeId, @NotNull(message = "email.null") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "email.invalid") String emailId, @NotNull(message = "phone.null") @Pattern(regexp = "^[0-9]{10}$", message = "phone.invalid") String phoneNumber) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public EmployeebasicDetails() {
    }

    @Override
    public String toString() {
        return "EmployeebasicDetails{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
