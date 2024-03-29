package service;
import backend.datarepo.details.Employee;

import java.util.List;

public class EmployeewebService {

    private List<Employee> employees ;

    public EmployeewebService(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public void setEmployees(List<Employee> employees){
        this.employees=employees;
    }

    public EmployeewebService() {

    }

    @Override
    public String toString() {
        return "EmployeewebService{" +
                "employees=" + employees +
                '}';
    }
}
