package service;

import backend.datarepo.DatabaseRepositoryImplementation;
import backend.datarepo.details.Employee;
import backend.datarepo.details.InputEmployeeDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeWebServiceImplementation {
    private InputEmployeeDetails inputEmployeeDetails;

    @WebMethod
    @WebResult(name = "employeeByPin")
    public EmployeewebService displayBasedOnPinCode(@WebParam(name = "pinCode") int pinCode) {
        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
        List<Employee> employees = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
        EmployeewebService employeewebService=new EmployeewebService();
        employeewebService.setEmployees(employees);
        return employeewebService;
    }
//    @WebMethod
//    @WebResult(name="EmployeeById")
//    public EmployeewebService displayBasedOnEmployeeId(@WebParam(name="employeeId")String employeeId){
//        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
//        Employee employees = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
//        EmployeewebService employeewebService=new EmployeewebService();
//        employeewebService.setEmployees(employees);
//        return employeewebService;
//    }
}

