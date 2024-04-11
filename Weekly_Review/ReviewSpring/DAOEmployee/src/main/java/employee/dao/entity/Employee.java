package employee.dao.entity;

public class Employee {
        EmployeebasicDetails employeebasicDetails;
        EmployeeAddress employeePermanentAddress;
        EmployeeAddress employeeTemporaryAddress;

        public Employee() {

        }
        @Override
        public String toString() {
            return "Employee{" +
                    "employeebasicDetails=" + employeebasicDetails +
                    ", employeePermanentAddress=" + employeePermanentAddress +
                    ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                    '}';
        }
        public String displayEmployeeDetails() {
            return "Employee ID: " + employeebasicDetails.getEmployeeId() +
                    "\nName: " + employeebasicDetails.getEmployeeName() +
                    "\nEmail: " + employeebasicDetails.getEmailId() +
                    "\nPhone Number: " + employeebasicDetails.getPhoneNumber() +
                    "\nPermanent Address: " + employeePermanentAddress.getAddress() +
                    "\nPermanent House Number: " + employeePermanentAddress.getHouseNumber() +
                    "\nPermanent City: " + employeePermanentAddress.getCity() +
                    "\nPermanent State: " + employeePermanentAddress.getState() +
                    "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                    "\nTemporary Address: " + employeeTemporaryAddress.getAddress() +
                    "\nTemporary House Number: " + employeeTemporaryAddress.getHouseNumber() +
                    "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                    "\nTemporary State: " + employeeTemporaryAddress.getState() +
                    "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
        }

        public EmployeebasicDetails getEmployeebasicDetails() {
            return employeebasicDetails;
        }

        public void setEmployeebasicDetails(EmployeebasicDetails employeebasicDetails) {
            this.employeebasicDetails = employeebasicDetails;
        }

        public EmployeeAddress getEmployeePermanentAddress() {
            return employeePermanentAddress;
        }

        public void setEmployeePermanentAddress(EmployeeAddress employeePermanentAddress) {
            this.employeePermanentAddress = employeePermanentAddress;
        }

        public EmployeeAddress getEmployeeTemporaryAddress() {
            return employeeTemporaryAddress;
        }

        public void setEmployeeTemporaryAddress(EmployeeAddress employeeTemporaryAddress) {
            this.employeeTemporaryAddress = employeeTemporaryAddress;
        }

        public Employee(EmployeebasicDetails employeebasicDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
            this.employeebasicDetails = employeebasicDetails;
            this.employeePermanentAddress = employeePermanentAddress;
            this.employeeTemporaryAddress = employeeTemporaryAddress;
        }
    }

