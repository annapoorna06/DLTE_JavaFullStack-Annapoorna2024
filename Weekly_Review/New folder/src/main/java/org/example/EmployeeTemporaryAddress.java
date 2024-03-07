package org.example;

public class EmployeeTemporaryAddress {
    private String temporaryAddress;
    private String temporaryHouseNumber;
    private String temporaryState;
    private String temporaryCity;
    private Integer temporaryPinCode;

    public EmployeeTemporaryAddress(String temporaryAddress, String temporaryHouseNumber, String temporaryState, String temporaryCity, Integer temporaryPinCode) {
        this.temporaryAddress = temporaryAddress;
        this.temporaryHouseNumber = temporaryHouseNumber;
        this.temporaryState = temporaryState;
        this.temporaryCity = temporaryCity;
        this.temporaryPinCode = temporaryPinCode;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getTemporaryHouseNumber() {
        return temporaryHouseNumber;
    }

    public void setTemporaryHouseNumber(String temporaryHouseNumber) {
        this.temporaryHouseNumber = temporaryHouseNumber;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public String getTemporaryCity() {
        return temporaryCity;
    }

    public void setTemporaryCity(String temporaryCity) {
        this.temporaryCity = temporaryCity;
    }

    public Integer getTemporaryPinCode() {
        return temporaryPinCode;
    }

    public void setTemporaryPinCode(Integer temporaryPinCode) {
        this.temporaryPinCode = temporaryPinCode;
    }
}
