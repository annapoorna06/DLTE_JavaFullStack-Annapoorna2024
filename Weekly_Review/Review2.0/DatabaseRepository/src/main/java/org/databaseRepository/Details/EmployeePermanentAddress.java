package org.databaseRepository.Details;

public class EmployeePermanentAddress {
    private String permanentAddress;
    private String permanentHouseNumber;
    private String permanentState;
    private String permanentCity;
    private Integer permanentPinCode;

    public EmployeePermanentAddress(String permanentAddress, String permanentHouseNumber, String permanentState, String permanentCity, Integer permanentPinCode) {
        this.permanentAddress = permanentAddress;
        this.permanentHouseNumber = permanentHouseNumber;
        this.permanentState = permanentState;
        this.permanentCity = permanentCity;
        this.permanentPinCode = permanentPinCode;
    }

    @Override
    public String toString() {
        return "EmployeePermanentAddress{" +
                "permanentAddress='" + permanentAddress + '\'' +
                ", permanentHouseNumber='" + permanentHouseNumber + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentPinCode=" + permanentPinCode +
                '}';
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentHouseNumber() {
        return permanentHouseNumber;
    }

    public void setPermanentHouseNumber(String permanentHouseNumber) {
        this.permanentHouseNumber = permanentHouseNumber;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public Integer getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(Integer permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }
}
