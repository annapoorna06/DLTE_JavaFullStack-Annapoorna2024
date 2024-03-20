package frontend.console.pojo;

public class EmployeeConsole {

        private EmployeebasicDetailsConsole basicDetails;
        private EmployeeAddressConsole permanentAddress;
        private EmployeeAddressConsole temporaryAddress;

        public EmployeeConsole(EmployeebasicDetailsConsole basicDetails, EmployeeAddressConsole permanentAddress, EmployeeAddressConsole temporaryAddress) {
            this.basicDetails = basicDetails;
            this.permanentAddress = permanentAddress;
            this.temporaryAddress = temporaryAddress;
        }

    public EmployeeConsole() {
    }

    public EmployeebasicDetailsConsole getBasicDetails() {
        return basicDetails;
    }

    public void setBasicDetails(EmployeebasicDetailsConsole basicDetails) {
        this.basicDetails = basicDetails;
    }

    public EmployeeAddressConsole getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddressConsole permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddressConsole getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddressConsole temporaryAddress) {
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

