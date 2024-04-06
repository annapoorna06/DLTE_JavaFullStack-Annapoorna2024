package employee.dao.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeAddress {
        private String address;

        @NotNull(message = "{address.houseNumber.null}")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{address.houseNumber.invalid}")
        private String houseNumber;

        @NotNull(message = "{address.state.null}")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.state.invalid}")
        private String state;

        @NotNull(message = "{address.city.null}")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.city.invalid}")
        private String city;

        @NotNull(message = "{address.pinCode.null}")
        @Digits(integer = 6, fraction = 0, message = "{address.pinCode.invalid}")
        private Integer pinCode;

    public EmployeeAddress(String address, @NotNull(message = "{address.houseNumber.null}") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{address.houseNumber.invalid}") String houseNumber, @NotNull(message = "{address.state.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.state.invalid}") String state, @NotNull(message = "{address.city.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.city.invalid}") String city, @NotNull(message = "{address.pinCode.null}") @Digits(integer = 6, fraction = 0, message = "{address.pinCode.invalid}") Integer pinCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "address='" + address + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                '}';
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

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
}


