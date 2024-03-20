package frontend.console.pojo;

public class EmployeeAddressConsole {
        private String address;
        private String houseNumber;
        private String state;
        private String city;
        private int pinCode;

        public EmployeeAddressConsole(String address, String houseNumber, String state, String city, int pinCode) {
            this.address = address;
            this.houseNumber = houseNumber;
            this.state = state;
            this.city = city;
            this.pinCode = pinCode;
        }

    public EmployeeAddressConsole() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}


