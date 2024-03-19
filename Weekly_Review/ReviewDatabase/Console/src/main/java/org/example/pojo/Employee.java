package org.example.pojo;

public class Employee {

        private EmployeebasicDetails basicDetails;
        private EmployeeAddress permanentAddress;
        private EmployeeAddress temporaryAddress;

        public Employee(EmployeebasicDetails basicDetails, EmployeeAddress permanentAddress, EmployeeAddress temporaryAddress) {
            this.basicDetails = basicDetails;
            this.permanentAddress = permanentAddress;
            this.temporaryAddress = temporaryAddress;
        }


    public EmployeebasicDetails getBasicDetails() {
        return basicDetails;
    }

    public void setBasicDetails(EmployeebasicDetails basicDetails) {
        this.basicDetails = basicDetails;
    }

    public EmployeeAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    // Additional method to display employee details
        public String displayEmployeeDetails() {
            return "Employee ID: " + basicDetails.getId() + "\n" +
                    "Name: " + basicDetails.getName() + "\n" +
                    "Email: " + basicDetails.getEmail() + "\n" +
                    "Phone Number: " + basicDetails.getPhoneNumber() + "\n" +
                    "Permanent Address: " + permanentAddress.getAddress() + ", " +
                    permanentAddress.getHouseNumber() + ", " +
                    permanentAddress.getCity() + ", " +
                    permanentAddress.getState() + ", " +
                    permanentAddress.getPinCode() + "\n" +
                    "Temporary Address: " + temporaryAddress.getAddress() + ", " +
                    temporaryAddress.getHouseNumber() + ", " +
                    temporaryAddress.getCity() + ", " +
                    temporaryAddress.getState() + ", " +
                    temporaryAddress.getPinCode();
        }
    }

