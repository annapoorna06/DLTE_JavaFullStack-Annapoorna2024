package org.example;

import java.util.List;

public class EmployeeDetailsOutput implements DisplayInfo {
//        public static void displayInput(List<Employee> employees) {
//        if (employees.isEmpty()){
//            System.out.println("No employee added yet.");
//        }
//        else{
//            System.out.println("employee Details:");
//            for(Employee emp: employees){
//                System.out.println("Name= "+emp.getEmployeeName());
//                System.out.println("Permanent Address :" + emp.getPermanentAddress().getPermanentAddress()+","+emp.getPermanentAddress().getPermanentHouseNumber()+","+emp.getPermanentAddress().getPermanentCity()+","+emp.getPermanentAddress().getPermanentState()+"-"+emp.getPermanentAddress().getPermanentPinCode());
//
//                System.out.println("Temporary Address :"+emp.getTemporaryAddress().getTemporaryAddress()+","+emp.getTemporaryAddress().getTemporaryHouseNumber()+","+emp.getTemporaryAddress().getTemporaryCity()+","+emp.getTemporaryAddress().getTemporaryState()+"-"+emp.getTemporaryAddress().getTemporaryPinCode());
//                System.out.println("Email id :" + emp.getAdditionalInfo().getEmailId() + "\nPhone number :" + emp.getAdditionalInfo().getPhoneNumber());
//                System.out.println(" ");
//
//            }
//        }
//
//
//    }
    @Override
    public void displayEmployee(List<Employee> employeeList) {
        if (employeeList.isEmpty()){
            System.out.println("No employee added yet.");
        }
        else{
            System.out.println("employee Details:");
            for(Employee emp: employeeList){
                System.out.println("Name= "+emp.getEmployeeName());
                System.out.println("Email id :" + emp.getAdditionalInfo().getEmailId() + "\nPhone number :" + emp.getAdditionalInfo().getPhoneNumber());
                System.out.println(" ");
    }

    @Override
    public void displayTemporaryAddress(List<Employee> employee) {

    }

    @Override
    public void displayPermanentAddress() {

    }
}
